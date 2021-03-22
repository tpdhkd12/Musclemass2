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

public class Changepassword extends AppCompatActivity {


    private String changepassword;

    ArrayList<Userinfo> userinfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        loadData();

        changepassword = userinfo.get(0).getPw();

        // 현재 비밀번호 입력 칸 //
        EditText write_password = (EditText) findViewById(R.id.write_password );

        // 현재 비밀번호와 일치하는지 확인 //
        Button current_password_accept = (Button) findViewById(R.id.current_password_accept);
        current_password_accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (write_password.getText().toString().equals(changepassword)) {


                            Intent intent = new Intent(v.getContext(), ChangePW_change.class);
                            startActivity(intent);
                            finish();

                        }else {

                            Toast.makeText(getApplicationContext(), "현재 비밀번호와 일치하지 않습니다." , Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null){
            userinfo = new ArrayList<>();
        }

    }

//    private void saveData() {
//
//        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(userinfo);
//        editor.putString("register", json);
//        editor.commit();
//    }
}