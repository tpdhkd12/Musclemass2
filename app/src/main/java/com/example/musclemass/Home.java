package com.example.musclemass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Home extends AppCompatActivity {

    private String id;
    private String pw;
    private String name;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        final EditText login_id = (EditText) findViewById(R.id.login_id);
        final EditText login_pw = (EditText) findViewById(R.id.login_pw);



        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(
                new View.OnClickListener(){


                    public void onClick(View view) {

                        if (id.equals(login_id.getText().toString()) && pw.equals(login_pw.getText().toString())) {

                            Intent intent = new Intent(view.getContext(), Choice.class);
                            intent.putExtra("password", pw);
                            intent.putExtra("username", name);
                            intent.putExtra("usernickname",nickname);

                            startActivity(intent);

                        }else {

                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                        }

                    }
                });
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                        startActivityForResult(intent, 201);

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        id = data.getStringExtra("id");
        pw = data.getStringExtra("pw");
        name = data.getStringExtra("username");
        nickname = data.getStringExtra("usernickname");

        Toast.makeText(getApplicationContext(),id + pw + name + nickname, Toast.LENGTH_LONG).show();

        super.onActivityResult(requestCode, resultCode, data);

    }


}