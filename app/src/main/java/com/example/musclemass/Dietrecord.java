package com.example.musclemass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.musclemass.DietAdapter.calendarDays2;

public class Dietrecord extends AppCompatActivity {


    CalendarDay diet_date;

    private int Year;
    private int Month;
    private int Day;

    private TextView textView;

    private String connect_id;

    // 리사이클러뷰와 연결해줄 어댑터 //
    private DietAdapter mmAdapter;

    MaterialCalendarView materialCalendarView;

    private ArrayList<CalendarDay> calendarDays;


    // 전체 데이터 불러올 어레이리스트//
    private ArrayList<Diet_tag_item> load_diet;


    // 불러온 정보중 id가 같으면 여기에 저장해서 보여줌 //
    private ArrayList<Diet_item> save_diet;


    // 쉐어드에서 connect유저의 정보 불러올 리스트 //
    private ArrayList<Userinfo> userinfos;

    private RecyclerView recyclerView;

    private EventDecorator eventDecorator;

    // tag item과 dietitem 연결해줄 변수들 //
    private String foodname;

    private String carbohydrate;

    private String protein;

    private String fat;

    private CalendarDay day;

    private String id;

    private String timeStamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietrecord);

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.dietcalendarView);


        materialCalendarView.setSelectedDate(CalendarDay.today());
        diet_date = CalendarDay.today();


        Year = diet_date.getYear();
        Month = diet_date.getMonth() + 1;
        Day = diet_date.getDay();

        textView = (TextView) findViewById(R.id.date_text_diet);


        textView.setText(String.format("%d년 %d월 %d일", Year, Month, Day));


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


        // 전체 날짜에 저장된 메모들의 데이터 불러오기 Diet_tag_item //
        loadData();


        //전체 데이터(Diet_tag_item)에 저장된 id가 같으면 save 어레이리스트(Diet_item)에 저장함 //
        if (save_diet == null) {
            save_diet = new ArrayList<>();
        }
        for (int i = 0; i < load_diet.size(); i++) {
            if (connect_id.equals(load_diet.get(i).getId())) {

                foodname =load_diet.get(i).getFoodname();
                carbohydrate = load_diet.get(i).getCarbohydrate();
                protein = load_diet.get(i).getProtein();
                fat = load_diet.get(i).getFat();
                day = load_diet.get(i).getDay();
                id = load_diet.get(i).getId();
                timeStamp = load_diet.get(i).getTimeStamp();

                Diet_item diet_item = new Diet_item(foodname,carbohydrate,protein,fat,day,id,timeStamp);

                if (diet_date.equals(load_diet.get(i).getDay())){

                    save_diet.add(diet_item);

                }


            }
        }

        check();

        // 리사이클러뷰와 저장된 데이터 불러올 리스트 연결 //
        recyclerView = (RecyclerView) findViewById(R.id.diet_recyclerview1);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mmAdapter = new DietAdapter(this, save_diet);
        recyclerView.setAdapter(mmAdapter);
        mmAdapter.notifyDataSetChanged();


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                diet_date = date;
                Year = diet_date.getYear();
                Month = diet_date.getMonth() + 1;
                Day = diet_date.getDay();

                textView.setText(String.format("%d년 %d월 %d일", Year, Month, Day));

                load_diet.clear();

                loadData();

                save_diet.clear();


                for (int i = 0; i < load_diet.size(); i++) {
                    if (diet_date.equals(load_diet.get(i).getDay())) {

                        foodname =load_diet.get(i).getFoodname();
                        carbohydrate = load_diet.get(i).getCarbohydrate();
                        protein = load_diet.get(i).getProtein();
                        fat = load_diet.get(i).getFat();
                        day = load_diet.get(i).getDay();
                        id = load_diet.get(i).getId();
                        timeStamp = load_diet.get(i).getTimeStamp();

                        Diet_item diet_item = new Diet_item(foodname,carbohydrate,protein,fat,day,id,timeStamp);

                        save_diet.add(diet_item);


                    }

                }

                check();

                mmAdapter.notifyDataSetChanged();
            }
        });


        ImageButton prior_dietrecord = (ImageButton) findViewById(R.id.prior_dietrecord);
        prior_dietrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent prior_intent = new Intent(view.getContext(), Choice.class);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );
//
//        Button watch_dietchart = (Button) findViewById(R.id.watch_dietchart);
//        watch_dietchart.setOnClickListener(
//                new View.OnClickListener() {
//                    public void onClick(View view) {
//
//                        Intent intent = new Intent(view.getContext(), Dietchart.class);
//
//                        startActivity(intent);
//                    }
//                }
//        );


        // 음식 기록 으로 이동하는 버튼 //
        Button add_dietrecord = (Button) findViewById(R.id.add_dietrecord);
        add_dietrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), Dietplus.class);
                        intent.putExtra("select_date_diet", diet_date);
                        startActivity(intent);
                    }
                }
        );


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        Intent prior_intent = new Intent(this, Choice.class);

        startActivity(prior_intent);

        finish();


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("diet_record", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("diet_record", "");
        Type type = new TypeToken<ArrayList<Diet_tag_item>>() {
        }.getType();
        load_diet = gson.fromJson(json, type);

        if (load_diet == null) {
            load_diet = new ArrayList<>();
        }

    }

    public void check() {

        if (calendarDays == null) {
            calendarDays = new ArrayList<>();
        }

        calendarDays.clear();

        for (int i = 0; i < load_diet.size(); i++) {
            if (load_diet.get(i).getId().equals(connect_id)) {
                // 데이터가 저장되어있는 날짜에 점 찍어주기 //

                calendarDays.add(load_diet.get(i).getDay());

            }
        }
        eventDecorator = new EventDecorator(Color.GREEN, calendarDays);
        materialCalendarView.addDecorators(
                eventDecorator
        );

    }
    public void check2() {

        calendarDays.clear();
        for (int i = 0; i <calendarDays2.size() ; i++){
            calendarDays.add(calendarDays2.get(i));
        }

        materialCalendarView.addDecorator(eventDecorator);

    }

}