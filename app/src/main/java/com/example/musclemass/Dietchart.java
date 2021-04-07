package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Dietchart extends AppCompatActivity {


    // 차트, 일 , 기록들 저장할 공간들 만들어주기 //
    BarChart carbychart;
    ArrayList<BarEntry> carbyArray;
    ArrayList<String> carbylabelname;

    // 전체 내용 가져올 어레이리스트 //
    ArrayList<Diet_tag_item> load_diet;
    ArrayList<Userinfo> userinfos;
    String connect_id;


    // 조정해서 담아줄 어레이//
    ArrayList<Diet_tag_item> select_diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietchart);

        carbychart = findViewById(R.id.carbohydrate_chart);
        carbyArray = new ArrayList<>();
        carbylabelname = new ArrayList<>();


        // 쉐어드에 저장된 현재 접속한 유저 정보를 저장된 리사이클러뷰 데이터와 비교해줄 변수에 담아줌 //
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser", "");
        Type type = new TypeToken<ArrayList<Userinfo>>() {
        }.getType();
        userinfos = gson.fromJson(json, type);

        if (userinfos == null) {
            userinfos = new ArrayList<>();
        }
        // 현재 접속한 유저의 정보를 받아옴 //
        connect_id = userinfos.get(0).getId();


        // 전체 기록된 내용 저장 후 뿌려주기 //
        loadData();


        ImageButton prior_dietchart = (ImageButton) findViewById(R.id.prior_dietchart);
        prior_dietchart.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent prior_intent = new Intent(view.getContext(), Dietrecord.class);
                        prior_intent.putExtra("username", save_name);
                        prior_intent.putExtra("usernickname", save_nickname);
                        startActivity(prior_intent);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent profile_getintent = getIntent();
        String save_name = profile_getintent.getStringExtra("username");
        String save_nickname = profile_getintent.getStringExtra("usernickname");


        Intent prior_intent = new Intent(this, Dietrecord.class);
        prior_intent.putExtra("username", save_name);
        prior_intent.putExtra("usernickname", save_nickname);

        startActivity(prior_intent);

        finish();


    }

    public void loadData() {

        // 전체 기록된 어레이 내용 가져왔음 //
        SharedPreferences sharedPreferences = getSharedPreferences("diet_record", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("diet_record", "");
        Type type = new TypeToken<ArrayList<Diet_tag_item>>() {
        }.getType();
        load_diet = gson.fromJson(json, type);

        if (load_diet == null) {
            load_diet = new ArrayList<>();
        }

        if (select_diet == null) {
            select_diet = new ArrayList<>();
        }
        select_diet.clear();
        for (int i = 0; i < load_diet.size(); i++) {
            if (connect_id.equals(load_diet.get(i).getId())) {

                // 날짜와 비교해서 어레이리스트 따로 저장해줘야함 //
                if (load_diet.get(i).getDay().equals(load_diet.get(i).getDay())){
                    //
                }

            }
        }

    }
}