package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Muscleplus extends AppCompatActivity {


    // RecyclerView item 어레이리스트 //
    private ArrayList<Muscle_item> muscle_itemArrayList;

    // 저장된 아이템 어레이리스트 //
    private ArrayList<Muscle_item> save_itemArrayList;

    //현재 접속한 유저 어레이리스트/ /
    ArrayList<Userinfo> userinfo;


    private MusclePlusAdapter mAdapter;

    private String save_kg;
    private String save_count;
    private String save_set;

    CalendarDay day;

    private String connect_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muscleplus);


        // 캘린더에서 받아온 날짜 변수에 넣어주기 //
        day = (CalendarDay) getIntent().getParcelableExtra("select_date");

        // id에 현재 접속한 id 넣어주기 //

        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser", "");
        Type type = new TypeToken<ArrayList<Userinfo>>() {
        }.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null) {
            userinfo = new ArrayList<>();
        }
        // 현재 접속한 유저의 정보를 받아옴 //
        connect_id = userinfo.get(0).getId();

        // 저장되어있는 아이템어레이리스트 불러오기//
        loadData();


        // 리사이클러뷰와 아이템 연결해주는 곳 //
        RecyclerView muscleRecyclerView = (RecyclerView) findViewById(R.id.muscle_recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        muscleRecyclerView.setLayoutManager(mLinearLayoutManager);

        muscle_itemArrayList = new ArrayList<>();
        mAdapter = new MusclePlusAdapter(this, muscle_itemArrayList);
        muscleRecyclerView.setAdapter(mAdapter);


        Button bench_plus = (Button) findViewById(R.id.bench_add);
        bench_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText bench_kg = (EditText) findViewById(R.id.bench_kg);
                EditText bench_count = (EditText) findViewById(R.id.bench_count);
                EditText bench_set = (EditText) findViewById(R.id.bench_set);

                save_kg = bench_kg.getText().toString();
                save_count = bench_count.getText().toString();
                save_set = bench_set.getText().toString();


                if (save_kg != null && save_count != null && save_set != null) {

                    Muscle_item data = new Muscle_item("벤치프레스 : " + save_kg + " kg", "  " + save_count + " 회", " " + save_set + " 세트", day, connect_id, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));

                    muscle_itemArrayList.add(data);

                    mAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(getApplicationContext(), "입력되지 않은 값이 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button squart_add = (Button) findViewById(R.id.squart_add);
        squart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText squart_kg = (EditText) findViewById(R.id.squart_kg);
                EditText squart_count = (EditText) findViewById(R.id.squart_count);
                EditText squart_set = (EditText) findViewById(R.id.squart_set);

                save_kg = squart_kg.getText().toString();
                save_count = squart_count.getText().toString();
                save_set = squart_set.getText().toString();


                if (save_kg != null && save_count != null && save_set != null) {

                    Muscle_item data = new Muscle_item("스쿼트 : " + save_kg + " kg", "  " + save_count + " 회", " " + save_set + " 세트", day, connect_id, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));

                    muscle_itemArrayList.add(data);

                    mAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(getApplicationContext(), "입력되지 않은 값이 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button dead_add = (Button) findViewById(R.id.dead_add);
        dead_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText dead_kg = (EditText) findViewById(R.id.dead_kg);
                EditText dead_count = (EditText) findViewById(R.id.dead_count);
                EditText dead_set = (EditText) findViewById(R.id.dead_set);

                save_kg = dead_kg.getText().toString();
                save_count = dead_count.getText().toString();
                save_set = dead_set.getText().toString();

                if (save_kg != null && save_count != null && save_set != null) {

                    Muscle_item data = new Muscle_item("데드리프트 : " + save_kg + " kg", "  " + save_count + " 회", " " + save_set + " 세트", day, connect_id, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));

                    muscle_itemArrayList.add(data);

                    mAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(getApplicationContext(), "입력되지 않은 값이 있습니다. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });


        ImageButton prior_muscleplus = (ImageButton) findViewById(R.id.prior_muscleplus);
        prior_muscleplus.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent prior_intent = new Intent(view.getContext(), Musclerecord.class);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );

        //운동을 다 Recyclerview 아이템에 추가하고 마지막 기록 버튼 //
        Button muscleplus_record = (Button) findViewById(R.id.muscleplus_record);
        muscleplus_record.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        // 운동을 다 추가하고 기록 할 때 나와있는 item 어레이리스트를 id와 날짜와 함께 추가 //
                        for (int i = 0; i < muscle_itemArrayList.size(); i++) {

                            save_itemArrayList.add(muscle_itemArrayList.get(i));


                        }


                        // 추가한 어레이리스트 저장 //
                        SharedPreferences preferences = getSharedPreferences("muscle_record", MODE_PRIVATE);
                        SharedPreferences.Editor muscle_edit = preferences.edit();
                        Gson gson1 = new Gson();
                        String json1 = gson1.toJson(save_itemArrayList);
                        muscle_edit.putString("muscle_record", json1);
                        muscle_edit.commit();


                        Intent intent = new Intent(view.getContext(), Musclerecord.class);
                        startActivity(intent);

                    }
                }
        );


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        Intent prior_intent = new Intent(this, Dietrecord.class);
        startActivity(prior_intent);

        finish();


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("muscle_record", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("muscle_record", "");
        Type type = new TypeToken<ArrayList<Muscle_item>>() {
        }.getType();
        save_itemArrayList = gson.fromJson(json, type);

        if (save_itemArrayList == null) {
            save_itemArrayList = new ArrayList<>();
        }

    }
}