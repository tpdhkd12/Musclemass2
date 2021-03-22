package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChangePW_change extends AppCompatActivity {

    ArrayList<Userinfo> connectuser;

    ArrayList<Userinfo> saveinfo ;

    private String id;
    private String pw;
    private String name;
    private String nickname;
    private String changepw;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword_change);

        //현재 접속한 유저 정보 갱신 //
        loadData();

        // 전체 유저정보 가져오기 //
        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("register","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        saveinfo = gson.fromJson(json, type);

        if (saveinfo == null){
            saveinfo = new ArrayList<>();
        }



        id = connectuser.get(0).getId();
        name = connectuser.get(0).getName();
        nickname = connectuser.get(0).getNickname();

        EditText changepwaccept = (EditText) findViewById(R.id.changepwaccept);
        EditText changepwaccept_confirm = (EditText) findViewById(R.id.changepwaccept_confirm);


        Button changepassword_accept = (Button) findViewById(R.id.changepassword_accept);
        changepassword_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (changepwaccept.getText().toString().equals(changepwaccept_confirm.getText().toString())) {
                    changepw = changepwaccept_confirm.getText().toString();
                    // 여기에서 입력한 정보 저장 //
                    saveData();

                    Intent intent = new Intent(v.getContext(), Profile.class);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(getApplicationContext(), "비밀번호와 비밀번호 확인이 일치하지 않습니다." , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        connectuser = gson.fromJson(json, type);

        if (connectuser == null){
            connectuser = new ArrayList<>();
        }

    }

    private void saveData() {

        for (int i = 0; i<saveinfo.size(); i++){
            if (id.equals(saveinfo.get(i).getId())){
                pw = changepw;
                Userinfo user = new Userinfo(id,pw,name,nickname);


                // 전체 유저정보의 index에 맞는 정보들을 이제 바꿔줌 //
                saveinfo.set(i,user);

                // 데이터 다시 저장 //
                SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(saveinfo);
                editor.putString("register", json);
                editor.commit();


            }
        }
    }
}