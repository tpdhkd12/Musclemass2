package com.example.musclemass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {

    private Dietrecord mContext;



    private ArrayList<Diet_item> mList;

    ArrayList<Userinfo> userinfoArrayList;

    private ArrayList<Diet_item> load_diets;

    String connect_diet_id;

    static ArrayList<CalendarDay> calendarDays2 = new ArrayList<>();

    public DietAdapter(Dietrecord context, ArrayList<Diet_item> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DietAdapter.DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_item, parent, false);

        DietViewHolder vh = new DietViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.DietViewHolder holder, int position) {


        holder.foodname.setText(mList.get(position).getFoodname());

        holder.carbohydrate.setText(mList.get(position).getCarbohydrate());

        holder.protein.setText(mList.get(position).getProtein());

        holder.fat.setText(mList.get(position).getFat());





    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class DietViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        protected TextView foodname;


        protected TextView carbohydrate;

        protected TextView protein;

        protected TextView fat;


        // 어레이리스트를 리사이클러뷰에게 보여주기위한 코드 //

        public DietViewHolder(@NonNull View itemView) {
            super(itemView);

            this.foodname = (TextView) itemView.findViewById(R.id.foodname);

            this.carbohydrate = (TextView) itemView.findViewById(R.id.carbohydrate);

            this.protein = (TextView) itemView.findViewById(R.id.protein);

            this.fat = (TextView) itemView.findViewById(R.id.fat);

            itemView.setOnCreateContextMenuListener(this);


        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);


        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case 1001:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextfoodname = (EditText) view.findViewById(R.id.edittext_dialog_foodname);
                        final EditText editTextcarby = (EditText) view.findViewById(R.id.edittext_dialog_carby);
                        final EditText editTextprot = (EditText) view.findViewById(R.id.edittext_dialog_prot);
                        final EditText editTextfat = (EditText) view.findViewById(R.id.edittext_dialog_fat);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextfoodname.setText(mList.get(getAdapterPosition()).getFoodname());
                        editTextcarby.setText(mList.get(getAdapterPosition()).getCarbohydrate());
                        editTextprot.setText(mList.get(getAdapterPosition()).getProtein());
                        editTextfat.setText(mList.get(getAdapterPosition()).getFat());


                        // 전체 저장된 식단 기록을 불러옴 //
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("diet_record", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = sharedPreferences.getString("diet_record", "");
                        Type type = new TypeToken<ArrayList<Diet_item>>() {
                        }.getType();
                        load_diets = gson.fromJson(json, type);

                        if (load_diets == null) {
                            load_diets = new ArrayList<>();
                        }

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String Strfoodname = editTextfoodname.getText().toString();
                                String Strcarby = editTextcarby.getText().toString();
                                String Strprot = editTextprot.getText().toString();
                                String Strfat = editTextfat.getText().toString();
                                CalendarDay day = (mList.get(getAdapterPosition()).getDay());
                                String id = (mList.get(getAdapterPosition()).getId());
                                String timestamp = (mList.get(getAdapterPosition()).getTimeStamp());

                                Diet_item dict = new Diet_item(Strfoodname, Strcarby, Strprot, Strfat, day, id, timestamp);

                                mList.set(getAdapterPosition(), dict);

                                for (int i = 0; i < load_diets.size(); i++) {

                                    if (dict.getTimeStamp().equals(load_diets.get(i).getTimeStamp())) {

                                        load_diets.set(i, dict);

                                        // 추가한 어레이리스트 저장 //
                                        SharedPreferences preferences = mContext.getSharedPreferences("diet_record", MODE_PRIVATE);
                                        SharedPreferences.Editor diet_edit = preferences.edit();
                                        Gson gson1 = new Gson();
                                        String json1 = gson1.toJson(load_diets);
                                        diet_edit.putString("diet_record", json1);
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

                        SharedPreferences sharedPreferences4 = mContext.getSharedPreferences("connectuser", MODE_PRIVATE);
                        Gson gson4 = new Gson();
                        String json4 = sharedPreferences4.getString("connectuser", "");
                        Type type4 = new TypeToken<ArrayList<Userinfo>>() {
                        }.getType();
                        userinfoArrayList = gson4.fromJson(json4, type4);

                        if (userinfoArrayList == null) {
                            userinfoArrayList = new ArrayList<>();
                        }
                        // 현재 접속한 유저의 정보를 받아옴 //
                        connect_diet_id = userinfoArrayList.get(0).getId();

                        // 전체 정보에서 삭제할 부분을 찾아서 삭제해줌 //
                        SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("diet_record", MODE_PRIVATE);
                        Gson gson1 = new Gson();
                        String json1 = sharedPreferences1.getString("diet_record", "");
                        Type type1 = new TypeToken<ArrayList<Diet_item>>() {
                        }.getType();
                        load_diets = gson1.fromJson(json1, type1);

                        if (load_diets == null) {
                            load_diets = new ArrayList<>();
                        }

                        for (int i = 0; i < load_diets.size(); i++) {
                            if (mList.get(getAdapterPosition()).getTimeStamp().equals(load_diets.get(i).getTimeStamp())) {

                                load_diets.remove(i);
                            }
                        }
                        if (calendarDays2 !=null){

                            calendarDays2.clear();
                        }
                        for (int i = 0; i < load_diets.size(); i++) {
                            if (load_diets.get(i).getId().equals(connect_diet_id)) {
                                // 데이터가 저장되어있는 날짜에 점 찍어주기 //

                                calendarDays2.add(load_diets.get(i).getDay());

                            }
                        }


                        // 추가한 어레이리스트 저장 //
                        SharedPreferences preferences2 = mContext.getSharedPreferences("diet_record", MODE_PRIVATE);
                        SharedPreferences.Editor diet_edit = preferences2.edit();
                        Gson gson2 = new Gson();
                        String json2 = gson2.toJson(load_diets);
                        diet_edit.putString("diet_record", json2);
                        diet_edit.commit();

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        mContext.check2();


                        break;
                }
                return true;
            }
        };
    }


}