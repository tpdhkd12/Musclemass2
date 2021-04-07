package com.example.musclemass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<Communityitem> mData;

    Context context;
    String connect_id;
    ArrayList<Userinfo> userinfos;
    String item_saveid;
    private ArrayList<Communityitem> load_community;

    int position1;

    public RecyclerImageTextAdapter(Context context, ArrayList<Communityitem> list) {
        this.context = context;

        this.mData = list;

    }


    // 리스트뷰가 생성 될 때의 생명주기 oncreate//
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {




        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.community_recyclerview, parent, false);

        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        Communityitem item = mData.get(position);

        holder.community_head.setText(item.getTitleStr());
        holder.community_text.setText(item.getDescStr());
        holder.community_nickname.setText(item.getNickname());
        holder.community_date.setText(item.getDay());

        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return (null != mData ? mData.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView community_head;
        TextView community_text;
        TextView community_nickname;
        TextView community_date;

        ViewHolder(View itemView) {
            super(itemView);
            this.community_head = itemView.findViewById(R.id.recycler_header);
            this.community_text = itemView.findViewById(R.id.recycler_body);
            this.community_nickname = itemView.findViewById(R.id.recycler_nickname);
            this.community_date = itemView.findViewById(R.id.recycler_date);



            if (userinfos == null) {
                userinfos = new ArrayList<>();
            }

            // 쉐어드에 저장된 현재 접속한 유저 정보를 저장된 리사이클러뷰 데이터와 비교해줄 변수에 담아줌 //
            SharedPreferences sharedPreferences = context.getSharedPreferences("connectuser", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("connectuser", "");
            Type type = new TypeToken<ArrayList<Userinfo>>() {
            }.getType();
            userinfos = gson.fromJson(json, type);

            // 현재 접속한 유저의 정보를 받아옴 //
            connect_id = userinfos.get(0).getId();



            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    position1 = getAdapterPosition();
                    if(connect_id.equals(mData.get(position1).getIdStr())){

                        itemView.setOnCreateContextMenuListener(ViewHolder.this::onCreateContextMenu);


                    }


                    return false;
                }
            });




            // 아이템 클릭 이벤트 처리.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        Intent intent = new Intent(context, Community_confirm.class);
                        intent.putExtra("community_itemid", mData.get(pos).getTimestamp());
                        context.startActivity(intent);
                    }

                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "수정");
                MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
                Edit.setOnMenuItemClickListener(onEditMenu);
                Delete.setOnMenuItemClickListener(onEditMenu);

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case 1001:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(context)
                                .inflate(R.layout.community_editbox, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.commit);
                        final EditText editcommunityheader = (EditText) view.findViewById(R.id.edit_communityhead);
                        final EditText editcommunitybody = (EditText) view.findViewById(R.id.edit_communitybody);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editcommunityheader.setText(mData.get(getAdapterPosition()).getTitleStr());
                        editcommunitybody.setText(mData.get(getAdapterPosition()).getDescStr());


                        // 전체 저장된 식단 기록을 불러옴 //
                        SharedPreferences sharedPreferences = context.getSharedPreferences("community_item", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = sharedPreferences.getString("community_item", "");
                        Type type = new TypeToken<ArrayList<Communityitem>>() {
                        }.getType();
                        load_community = gson.fromJson(json, type);

                        if (load_community == null) {
                            load_community = new ArrayList<>();
                        }

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String Strhead = editcommunityheader.getText().toString();
                                String Strbody = editcommunitybody.getText().toString();
                                String Strid = mData.get(getAdapterPosition()).getIdStr();
                                String Strday = mData.get(getAdapterPosition()).getDay();
                                String timestamp = mData.get(getAdapterPosition()).getTimestamp();
                                String nickname = mData.get(getAdapterPosition()).getNickname();

                                Communityitem dict = new Communityitem(Strhead, Strbody, Strid, Strday, timestamp, nickname);

                                mData.set(getAdapterPosition(), dict);

                                for (int i = 0; i < load_community.size(); i++) {

                                    if (dict.getTimestamp().equals(load_community.get(i).getTimestamp())) {

                                        load_community.set(i, dict);

                                        // 추가한 어레이리스트 저장 //
                                        SharedPreferences preferences = context.getSharedPreferences("community_item", MODE_PRIVATE);
                                        SharedPreferences.Editor diet_edit = preferences.edit();
                                        Gson gson1 = new Gson();
                                        String json1 = gson1.toJson(load_community);
                                        diet_edit.putString("community_item", json1);
                                        diet_edit.commit();


                                    }


                                }


                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();


                            }
                        });
                        dialog.show();

                        break;

                    case 1002:
                        // 현재 접속한 유저의 id를 변수에 저장해줘야 리사이클러뷰를 뜨게 해줄 아이디와 비교 할 수 있음 //


                        // 전체 정보에서 삭제할 부분을 찾아서 삭제해줌 //
                        SharedPreferences sharedPreferences1 = context.getSharedPreferences("community_item", MODE_PRIVATE);
                        Gson gson1 = new Gson();
                        String json1 = sharedPreferences1.getString("community_item", "");
                        Type type1 = new TypeToken<ArrayList<Communityitem>>() {
                        }.getType();
                        load_community = gson1.fromJson(json1, type1);

                        if (load_community == null) {
                            load_community = new ArrayList<>();
                        }

                        for (int i = 0; i < load_community.size(); i++) {
                            if (mData.get(getAdapterPosition()).getTimestamp().equals(load_community.get(i).getTimestamp())) {

                                load_community.remove(i);
                            }
                        }


                        // 추가한 어레이리스트 저장 //
                        SharedPreferences preferences2 = context.getSharedPreferences("community_item", MODE_PRIVATE);
                        SharedPreferences.Editor diet_edit = preferences2.edit();
                        Gson gson2 = new Gson();
                        String json2 = gson2.toJson(load_community);
                        diet_edit.putString("community_item", json2);
                        diet_edit.commit();

                        mData.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mData.size());

                        break;
                }
                return true;
            }
        };

    }

}