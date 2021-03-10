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

    private String profile_name;
    private String profile_nickname;
    private String password;
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

        Intent gintent = getIntent();
        password = gintent.getStringExtra("password");
        profile_name = gintent.getStringExtra("username");
        profile_nickname = gintent.getStringExtra("usernickname");


        // 프로필로 이동 //
        goprofile = findViewById(R.id.goprofile);
        goprofile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), Profile.class);
                        intent.putExtra("username", profile_name);
                        intent.putExtra("usernickname", profile_nickname);
                        intent.putExtra("password",password);

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


                        Intent muscleintent = new Intent(v.getContext(), Musclerecord.class);
                        muscleintent.putExtra("username", profile_name);
                        muscleintent.putExtra("usernickname", profile_nickname);

                        startActivity(muscleintent);
                        finish();
                    }
                });
        // 식단 기록 액티비티로 이동 //
        godietrecord.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent dietrecordintent = new Intent(view.getContext(), Dietrecord.class);
                        dietrecordintent.putExtra("username",profile_name);
                        dietrecordintent.putExtra("usernickname",profile_nickname);
                        startActivity(dietrecordintent);

                        finish();
                    }
                }
        );
        // 보호대 구매 사이트 이동 //
        goprotectionsite.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent protectionintent = new Intent(view.getContext(), Protectionsite.class);
                        protectionintent.putExtra("username",profile_name);
                        protectionintent.putExtra("usernickname",profile_nickname);
                        startActivity(protectionintent);

                        finish();
                    }
                }
        );
        // 음악 들을 수 있는 사이트 이동 //
        gomusic.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        Intent musicintent = new Intent(view.getContext(), Music.class);
                        musicintent.putExtra("username",profile_name);
                        musicintent.putExtra("usernickname",profile_nickname);
                        startActivity(musicintent);

                        finish();
                    }
                }
        );
        // 커뮤니티 버튼 이동 //
        gocommunity.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent communityintent = new Intent(view.getContext(), Community.class);
                        communityintent.putExtra("username",profile_name);
                        communityintent.putExtra("usernickname",profile_nickname);
                        startActivity(communityintent);

                        finish();
                    }
                }
        );

    }
}
