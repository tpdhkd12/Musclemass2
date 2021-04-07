package com.example.musclemass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class Choice extends AppCompatActivity {

    private ImageButton goprofile;
    private ImageButton btn_gomusclerecord;
    private ImageButton godietrecord;
    private ImageButton gocommunity;

    boolean changeactivity = true;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);



        goprofile = findViewById(R.id.goprofile);
        btn_gomusclerecord = (ImageButton) findViewById(R.id.btn_gomusclerecord);
        godietrecord = (ImageButton) findViewById(R.id.godietrecord);
        gocommunity = (ImageButton) findViewById(R.id.gocommunity);

        changeactivity = true;

        Handler mhandler = new Handler(
                Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                TextView handlerText = (TextView) findViewById(R.id.handler_Text);

                if (msg.what == 0) {


                    if (i == 0) {

                        handlerText.setText("근육과 지방을 구분하는 것은 간단하다. 흔들리면 지방이다.\n -아놀드 슈왈 제네거 -");


                    } else if (i == 1) {

                        handlerText.setText("나의 최대 1rm이 누군가에겐 빈 봉이다. \n -황석준-");

                    } else if (i == 2) {

                        handlerText.setText("돼지가 되어도 건강한 돼지가 되어라. \n -김종국 -");


                    } else if (i == 3) {

                        handlerText.setText("운동은 먹는것까지가 운동이다. \n -김종국 -");


                    } else if (i == 4) {

                        handlerText.setText("The reason I exercise is for the quality of life I enjoy \n -Kenneth H. Cooper-");


                    } else if (i == 5) {

                        handlerText.setText("The only bad workout is the one that didn't happen \n -Se wang -");


                    } else if (i == 6) {

                        handlerText.setText("당신은 운동을 1시간을 해도 자신의 한계를 뛰어넘으려고 노력했나? \n -성기찬-");

                    } else if (i == 7){

                        handlerText.setText("나약한 육체는 없다. 나약한 의지만 있을 뿐이다. \n -작자 미상-");

                    }else if (i == 8){

                        handlerText.setText("Yeah! Buddy! Light weight baby ! \n -로니 콜먼-");
                    }

                }

            }


        };


        // 핸들러로 메인 ui에 넣어주고싶은 runnable 생성 //
        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (changeactivity) {
                    Random random = new Random();
                    i = random.nextInt(9);

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mhandler.sendEmptyMessage(0);
                }
            }
        }

        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();


        // 프로필로 이동 //
        goprofile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        changeactivity = false;

                        Intent intent = new Intent(view.getContext(), Profile.class);
                        startActivity(intent);
                        finish();
                    }


                }
        );

        //운동기록 액티비티로 이동 //
        btn_gomusclerecord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        changeactivity = false;
                        Intent muscleintent = new Intent(v.getContext(), Musclerecord.class);
                        startActivity(muscleintent);
                        finish();
                    }
                });
        // 식단 기록 액티비티로 이동 //
        godietrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        changeactivity = false;
                        Intent dietrecordintent = new Intent(view.getContext(), Dietrecord.class);
                        startActivity(dietrecordintent);
                        finish();

                    }
                }
        );
        // 커뮤니티 버튼 이동 //
        gocommunity.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        changeactivity = false;

                        Intent communityintent = new Intent(view.getContext(), Community.class);
                        startActivity(communityintent);
                        finish();

                    }
                }
        );


    }
}
