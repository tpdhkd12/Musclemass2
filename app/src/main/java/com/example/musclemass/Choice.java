package com.example.musclemass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Choice extends AppCompatActivity {
    private String id;
    private String pw;

    private ImageButton goprofile;
    private ImageButton btn_gomusclerecord;
    private ImageButton godietrecord;
    private ImageButton goprotectionsite;
    private ImageButton gomusic;
    private ImageButton gocommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);

        btn_gomusclerecord = (ImageButton) findViewById(R.id.btn_gomusclerecord);
        godietrecord = (ImageButton) findViewById(R.id.godietrecord);
        goprotectionsite = (ImageButton) findViewById(R.id.goprotectionsite);
        gomusic = (ImageButton) findViewById(R.id.gomusic);
        gocommunity = (ImageButton) findViewById(R.id.gocommunity);



        // 프로필로 이동 //
        goprofile = findViewById(R.id.goprofile);
        goprofile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), Profile.class);
                        startActivity(intent);
                    }


                }
        );

        //운동기록 액티비티로 이동 //
        btn_gomusclerecord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent muscleintent = new Intent(v.getContext(), Musclerecord.class);
                        startActivity(muscleintent);
                    }
                });
        // 식단 기록 액티비티로 이동 //
        godietrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent dietrecordintent = new Intent(view.getContext(), Dietrecord.class);
                        startActivity(dietrecordintent);

                    }
                }
        );
        // 보호대 구매 사이트 이동 //
        goprotectionsite.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent protectionintent = new Intent(view.getContext(), Protectionsite.class);
                        startActivity(protectionintent);

                    }
                }
        );
        // 음악 들을 수 있는 사이트 이동 //
        gomusic.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        Intent musicintent = new Intent(view.getContext(), Music.class);
                        startActivity(musicintent);


                    }
                }
        );
        // 커뮤니티 버튼 이동 //
        gocommunity.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent communityintent = new Intent(view.getContext(), Community.class);
                        startActivity(communityintent);

                    }
                }
        );

    }
}
