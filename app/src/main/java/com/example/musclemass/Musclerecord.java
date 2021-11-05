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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.musclemass.MuscleAdapter.calendarDays4;

public class Musclerecord extends AppCompatActivity implements Serializable {

    CalendarDay putdate;

    int Year;
    int Month;
    int Day;
    TextView textView;

    String connect_id;

    private MuscleAdapter mAdapter;

    boolean changeActivity;
    ArrayList<CalendarDay> calendarDays;

    EventDecorator eventDecorator;

    MaterialCalendarView materialCalendar;
    //전체 데이터를 불러올 어레이리스트 //
    ArrayList<Muscle_item> load_muscle;

    // 불러온 정보중에 id와 날짜가 같으면 저장할 어레이리스트 //
    ArrayList<Muscle_item> save_muscle;

    //유저 정보를 저장할 어레이리스트 //
    ArrayList<Userinfo> userinfo;

    // 리사이클러뷰 //
    RecyclerView recordRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musclerecord);


        materialCalendar= (MaterialCalendarView) findViewById(R.id.musclecalendarView);

        materialCalendar.setSelectedDate(CalendarDay.today());
        putdate = CalendarDay.today();




        // textview 에 날짜 표시되게 할 것 //
        Year = putdate.getYear();
        Month = putdate.getMonth() + 1;
        Day = putdate.getDay();
        textView = (TextView) findViewById(R.id.date_text);

        textView.setText(String.format("%d년 %d월 %d일", Year, Month, Day));


        // 현재 접속한 유저의 id를 변수에 저장해줘야 리사이클러뷰를 뜨게 해줄 아이디와 비교 할 수 있음 //

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


        // 저장되어있는 전체 날짜와 데이터 불러오기(load_muscle) //
        loadData();


        // id가 같으면 어레이리스트(save_muscle)에 저장 //
        if (save_muscle == null) {
            save_muscle = new ArrayList<>();
        }
        for (int i = 0; i < load_muscle.size(); i++) {
            if (load_muscle.get(i).getId().equals(connect_id)) {


                if (load_muscle.get(i).getDay().equals(putdate)) {

                    save_muscle.add(load_muscle.get(i));

                }


            }
        }

        check4();
        // 리사이클러뷰와 저장된 데이터 불러올 리스트 연결 //
        recordRecyclerView = (RecyclerView) findViewById(R.id.muscle_record_recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recordRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new MuscleAdapter(this, save_muscle);
        recordRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



        materialCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                putdate = date;
                Year = putdate.getYear();
                Month = putdate.getMonth() + 1;
                Day = putdate.getDay();

                textView.setText(String.format("%d년 %d월 %d일", Year, Month, Day));

                load_muscle.clear();

                loadData();

                save_muscle.clear();


                for (int i = 0; i < load_muscle.size(); i++) {
                    if (putdate.equals(load_muscle.get(i).getDay())) {
                        if (load_muscle.get(i).getId().equals(connect_id)) {


                            save_muscle.add(load_muscle.get(i));

                        }
                    }

                }

                check4();

                mAdapter.notifyDataSetChanged();
            }
        });


        ImageButton prior_musclerecord = (ImageButton) findViewById(R.id.prior_musclerecord);
        prior_musclerecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        Intent prior_intent = new Intent(view.getContext(), Choice.class);
                        startActivity(prior_intent);
                        finish();
                    }
                }
        );

//        Button watch_musclechart = (Button) findViewById(R.id.watch_musclechart);
//        watch_musclechart.setOnClickListener(
//                new View.OnClickListener() {
//                    public void onClick(View view) {
//
//                        changeActivity = false;
//                        Intent intent = new Intent(view.getContext(), Musclechart.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//        );

        // 운동기록으로 이동하는 버튼 //
        Button add_musclerecord = (Button) findViewById(R.id.add_musclerecord);
        add_musclerecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        changeActivity = false;

                        Intent intent = new Intent(view.getContext(), Muscleplus.class);
                        intent.putExtra("select_date", putdate);
                        startActivity(intent);
                        finish();


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

        changeActivity = false;

        Intent prior_intent = new Intent(this, Choice.class);
        prior_intent.putExtra("username", save_name);
        prior_intent.putExtra("usernickname", save_nickname);

        startActivity(prior_intent);

        finish();


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("muscle_record", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("muscle_record", "");
        Type type = new TypeToken<ArrayList<Muscle_item>>() {
        }.getType();
        load_muscle = gson.fromJson(json, type);

        if (load_muscle == null) {
            load_muscle = new ArrayList<>();
        }

    }
    public void check4() {

        if (calendarDays == null) {
            calendarDays = new ArrayList<>();
        }

        calendarDays.clear();

        for (int i = 0; i < load_muscle.size(); i++) {
            if (load_muscle.get(i).getId().equals(connect_id)) {
                // 데이터가 저장되어있는 날짜에 점 찍어주기 //

                calendarDays.add(load_muscle.get(i).getDay());

            }
        }
        eventDecorator = new EventDecorator(Color.GREEN, calendarDays);
        materialCalendar.addDecorators(
                eventDecorator
        );

    }
    public void check3() {

        calendarDays.clear();

        for (int i = 0; i <calendarDays4.size() ; i++){
            calendarDays.add(calendarDays4.get(i));
        }

        materialCalendar.addDecorator(eventDecorator);

    }

}