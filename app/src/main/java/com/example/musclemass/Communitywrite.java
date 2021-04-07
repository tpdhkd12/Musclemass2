package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

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

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Communitywrite extends AppCompatActivity {


    // 커뮤니티아이템 형태의 객체로 저장을 해야함 //
    ArrayList<Communityitem> save_itemArrayList;

    ArrayList<Userinfo> userinfos;

    private String connect_id;
    private String connect_nickname;

    EditText communityhead;
    EditText communityacc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitywrite);

        // user 데이터 로드해서 작성자 id 받아오기 //
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
        connect_nickname=userinfos.get(0).getNickname();


        // 전체 item Arraylist 가져올 것 //
        loadData();

        // 제목과 내용 id 찾아서 선언 //
        communityhead= (EditText) findViewById(R.id.communityhead);
        communityacc= (EditText) findViewById(R.id.communityacc);


        ImageButton prior_communitywrite = (ImageButton) findViewById(R.id.prior_communitywrite);
        prior_communitywrite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent prior_intent = new Intent(v.getContext(), Community.class);
                        startActivity(prior_intent);

                        finish();


                    }
                }
        );

        Button community_accept = (Button) findViewById(R.id.community_accept);
        community_accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 현재시간 받아오기 //
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("hh:mm");
                        String getTime = simpleDate.format(date);

                        // 아이템 객체를 만들어서 어레이리스트 안에 넣고 그걸 shared에 json 형태로 저장 //
                        Communityitem item = new Communityitem(communityhead.getText().toString(),communityacc.getText().toString(),connect_id,getTime,new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()),connect_nickname);
                        save_itemArrayList.add(item);

                        saveData();

                        Intent intent = new Intent(v.getContext(),Community.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent prior_intent = new Intent(getApplicationContext(), Community.class);
        startActivity(prior_intent);

        finish();


    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("community_item", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("community_item", "");
        Type type = new TypeToken<ArrayList<Communityitem>>() {
        }.getType();
        save_itemArrayList = gson.fromJson(json, type);

        if (save_itemArrayList == null) {
            save_itemArrayList = new ArrayList<>();
        }

    }
    private void saveData() {

        // 추가한 어레이리스트 저장 //
        SharedPreferences preferences = getSharedPreferences("community_item", MODE_PRIVATE);
        SharedPreferences.Editor muscle_edit = preferences.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(save_itemArrayList);
        muscle_edit.putString("community_item", json1);
        muscle_edit.commit();
    }

}