package com.example.musclemass;

import android.content.Context;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MusclePlusAdapter extends RecyclerView.Adapter<MusclePlusAdapter.MuscleViewHolder> {

    private Context mContext;


    private ArrayList<Muscle_item> mList;

    //유저 정보를 저장할 어레이리스트 //
    ArrayList<Userinfo> userinfo;

    //전체 데이터를 불러올 어레이리스트 //
    private ArrayList<Muscle_item> load_muscles;

    String connect_id;

    public MusclePlusAdapter(Context context, ArrayList<Muscle_item> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MusclePlusAdapter.MuscleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.muscle_item, parent, false);

        MuscleViewHolder vh = new MuscleViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MusclePlusAdapter.MuscleViewHolder holder, int position) {

        holder.kg.setText(mList.get(position).getKg());

        holder.count.setText(mList.get(position).getCount());

        holder.set.setText(mList.get(position).getSet());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class MuscleViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        protected TextView kg;

        protected TextView count;

        protected TextView set;


        public MuscleViewHolder(@NonNull View itemView) {
            super(itemView);

            this.kg = (TextView) itemView.findViewById(R.id.muscle_kg);

            this.count = (TextView) itemView.findViewById(R.id.muscle_count);

            this.set = (TextView) itemView.findViewById(R.id.muscle_set);

            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1003, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1004, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu1);
            Delete.setOnMenuItemClickListener(onEditMenu1);

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu1 = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case 1003:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box_muscle, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit1);
                        final EditText editTextkg = (EditText) view.findViewById(R.id.edittext_dialog_kg);
                        final EditText editTextcount = (EditText) view.findViewById(R.id.edittext_dialog_count);
                        final EditText editTextset = (EditText) view.findViewById(R.id.edittext_dialog_set);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextkg.setText(mList.get(getAdapterPosition()).getKg());
                        editTextcount.setText(mList.get(getAdapterPosition()).getCount());
                        editTextset.setText(mList.get(getAdapterPosition()).getSet());


                        // 전체 저장된 운동 기록 데이터들을 불러옴 //
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("muscle_record", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = sharedPreferences.getString("muscle_record", "");
                        Type type = new TypeToken<ArrayList<Muscle_item>>() {
                        }.getType();
                        load_muscles = gson.fromJson(json, type);

                        if (load_muscles == null) {
                            load_muscles = new ArrayList<>();
                        }


                        /// 수정 //
                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                String Strkg = editTextkg.getText().toString();
                                String Strcount = editTextcount.getText().toString();
                                String Strset = editTextset.getText().toString();
                                CalendarDay day = (mList.get(getAdapterPosition()).getDay());
                                String id = (mList.get(getAdapterPosition()).getId());
                                String timestamp = (mList.get(getAdapterPosition()).getTimeStamp());


                                Muscle_item dict = new Muscle_item(Strkg, Strcount, Strset, day, id, timestamp);
                                mList.set(getAdapterPosition(), dict);


                                for (int i = 0; i < load_muscles.size(); i++) {

                                    if (dict.getTimeStamp().equals(load_muscles.get(i).getTimeStamp())) {

                                        load_muscles.set(i, dict);

                                        // 추가한 어레이리스트 저장 //
                                        SharedPreferences preferences = mContext.getSharedPreferences("muscle_record", MODE_PRIVATE);
                                        SharedPreferences.Editor muscle_edit = preferences.edit();
                                        Gson gson1 = new Gson();
                                        String json1 = gson1.toJson(load_muscles);
                                        muscle_edit.putString("muscle_record", json1);
                                        muscle_edit.commit();


                                    }


                                }


                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();


                            }
                        });
                        dialog.show();

                        break;

                    case 1004:

                        // 현재 접속한 유저의 id를 변수에 저장해줘야 리사이클러뷰를 뜨게 해줄 아이디와 비교 할 수 있음 //

                        SharedPreferences sharedPreferences4 = mContext.getSharedPreferences("connectuser", MODE_PRIVATE);
                        Gson gson4 = new Gson();
                        String json4 = sharedPreferences4.getString("connectuser", "");
                        Type type4 = new TypeToken<ArrayList<Userinfo>>() {
                        }.getType();
                        userinfo = gson4.fromJson(json4, type4);

                        if (userinfo == null) {
                            userinfo = new ArrayList<>();
                        }
                        // 현재 접속한 유저의 정보를 받아옴 //
                        connect_id = userinfo.get(0).getId();

                        // 전체 정보에서 삭제할 부분을 찾아서 삭제해줌 //
                        SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("muscle_record", MODE_PRIVATE);
                        Gson gson1 = new Gson();
                        String json1 = sharedPreferences1.getString("muscle_record", "");
                        Type type1 = new TypeToken<ArrayList<Muscle_item>>() {
                        }.getType();
                        load_muscles = gson1.fromJson(json1, type1);

                        if (load_muscles == null) {
                            load_muscles = new ArrayList<>();
                        }

                        for (int i = 0; i < load_muscles.size(); i++) {
                            if (mList.get(getAdapterPosition()).getTimeStamp().equals(load_muscles.get(i).getTimeStamp())) {

                                load_muscles.remove(i);
                            }
                        }

                        // 추가한 어레이리스트 저장 //
                        SharedPreferences preferences2 = mContext.getSharedPreferences("muscle_record", MODE_PRIVATE);
                        SharedPreferences.Editor muscle_edit = preferences2.edit();
                        Gson gson2 = new Gson();
                        String json2 = gson2.toJson(load_muscles);
                        muscle_edit.putString("muscle_record", json2);
                        muscle_edit.commit();


                        // 삭제 //
                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());


                }
                return true;
            }
        };
    }
}

