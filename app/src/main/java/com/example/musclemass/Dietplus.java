package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dietplus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietplus);

        ImageButton prior_dietplus = (ImageButton) findViewById(R.id.prior_dietplus);
        prior_dietplus.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent prior_intent = new Intent(view.getContext(), Dietrecord.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);
                        startActivity(prior_intent);
                    }
                }
        );

        Button dietplus_record = (Button) findViewById(R.id.dietplus_record);
        dietplus_record.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(view.getContext(), Dietrecord.class);
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);
                        startActivity(intent);
                    }
                }
        );



    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent profile_getintent = getIntent();
        String save_name = profile_getintent.getStringExtra("username");
        String save_nickname = profile_getintent.getStringExtra("usernickname");


        Intent prior_intent = new Intent(this, Dietrecord.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);

        finish();



    }
}