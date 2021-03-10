package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText register_id = (EditText) findViewById(R.id.register_id);
        final EditText register_pw = (EditText) findViewById(R.id.register_pw);
        final EditText register_name = (EditText) findViewById(R.id.register_name);
        final EditText register_nickname = (EditText) findViewById(R.id.register_nickname);



        Button register_accept = (Button) findViewById(R.id.register_accept);
        register_accept.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        String id = register_id.getText().toString();
                        String pw = register_pw.getText().toString();
                        String name = register_name.getText().toString();
                        String nickname = register_nickname.getText().toString();

                        Intent registerintent = new Intent();
                        registerintent.putExtra("id",id);
                        registerintent.putExtra("pw",pw);
                        registerintent.putExtra("username",name);
                        registerintent.putExtra("usernickname",nickname);

                        setResult(RESULT_OK, registerintent);

                        finish();

                    }
                });
    }
}