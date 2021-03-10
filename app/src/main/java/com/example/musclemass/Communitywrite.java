package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Communitywrite extends AppCompatActivity {

    private String savehead;
    private String savetext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitywrite);

        Intent getintent = getIntent();
        savehead = getintent.getStringExtra("communityhead1");
        savetext = getintent.getStringExtra("communityacc1");
        startActivity(getintent);




        EditText communityhead = (EditText) findViewById(R.id.communityhead);
        EditText communityacc = (EditText) findViewById(R.id.communityacc);


        communityacc.setText(savetext);
        communityhead.setText(savehead);




        ImageButton prior_communitywrite = (ImageButton) findViewById(R.id.prior_communitywrite);
        prior_communitywrite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent intent = new Intent(v.getContext(), Community.class);
                        intent.putExtra("communityhead1",savehead);
                        intent.putExtra("communityacc1",savetext);
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);
                        startActivity(intent);
                    }
                }
        );

        Button community_accept = (Button) findViewById(R.id.community_accept);
        community_accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(v.getContext(), Community.class);
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);
                        intent.putExtra("communityhead",communityhead.getText().toString());
                        intent.putExtra("communityacc",communityacc.getText().toString());
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


        Intent prior_intent = new Intent(this, Choice.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);

        finish();



    }

}