package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<Userinfo> userinfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loadData();






        final EditText register_id = (EditText) findViewById(R.id.register_id);
        final EditText register_pw = (EditText) findViewById(R.id.register_pw);
        final EditText register_name = (EditText) findViewById(R.id.register_name);
        final EditText register_nickname = (EditText) findViewById(R.id.register_nickname);



        Button register_accept = (Button) findViewById(R.id.register_accept);
        register_accept.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {

                        Userinfo user = new Userinfo(register_id.getText().toString(),register_pw.getText().toString(),register_name.getText().toString(),register_nickname.getText().toString());

                        userinfo.add(user);

                        saveData();

                        Intent intent = new Intent(view.getContext(), Home.class);

                        startActivity(intent);
                        finish();

                    }
                });


    }
    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userinfo);
        editor.putString("register", json);
        editor.commit();
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