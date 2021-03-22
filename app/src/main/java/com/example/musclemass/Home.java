package com.example.musclemass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Home extends AppCompatActivity {

    private String id;
    private String pw;
    private String name;
    private String nickname;

    ArrayList<Userinfo> connectuser = new ArrayList<>();

    ArrayList<Userinfo> userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        final EditText login_id = (EditText) findViewById(R.id.login_id);
        final EditText login_pw = (EditText) findViewById(R.id.login_pw);


        loadData();


        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(
                new View.OnClickListener() {


                    public void onClick(View view) {

                            for (int i = 0; i<userinfo.size(); i++){

                                id = userinfo.get(i).getId();
                                pw = userinfo.get(i).getPw();
                                name = userinfo.get(i).getName();
                                nickname = userinfo.get(i).getNickname();

                                if (id.equals(login_id.getText().toString()) && pw.equals(login_pw.getText().toString())) {
                                    Userinfo userinfo = new Userinfo(id,pw,name,nickname);

                                    connectuser.add(userinfo);

                                    SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(connectuser);
                                    editor.putString("connectuser", json);
                                    editor.commit();

                                    Intent intent = new Intent(view.getContext(), Choice.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
//
//                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호가 일치하지 않습니다. " , Toast.LENGTH_SHORT).show();

                    }
                });
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                        startActivity(intent);

                    }
                });

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("register","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null){
            userinfo = new ArrayList<>();
        }

    }


}