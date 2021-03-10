package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Musclerecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musclerecord);

        ImageButton prior_musclerecord = (ImageButton) findViewById(R.id.prior_musclerecord);
        prior_musclerecord.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent prior_intent = new Intent(view.getContext(), Choice.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );

        Button watch_musclechart = (Button) findViewById(R.id.watch_musclechart);
        watch_musclechart.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname  = profile_getintent.getStringExtra("usernickname");
                        Intent intent = new Intent(view.getContext(), Musclechart.class);
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);

                        startActivity(intent);
                        finish();
                    }
                }
        );

        Button add_musclerecord = (Button) findViewById(R.id.add_musclerecord);
        add_musclerecord.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname  = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(view.getContext(), Muscleplus.class);
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);
                        startActivity(intent);
                        finish();
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


        Intent prior_intent = new Intent(this, Choice.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);

        finish();



    }
}