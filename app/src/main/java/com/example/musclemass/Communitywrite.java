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
    String save_nickname;
    private String head;
    private String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitywrite);

        EditText communityhead = (EditText) findViewById(R.id.communityhead);
        EditText communityacc = (EditText) findViewById(R.id.communityacc);

        Intent profile_getintent = getIntent();
        String save_name = profile_getintent.getStringExtra("username");
        save_nickname = profile_getintent.getStringExtra("usernickname");







        ImageButton prior_communitywrite = (ImageButton) findViewById(R.id.prior_communitywrite);
        prior_communitywrite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                        Intent intent = new Intent();
                        intent.putExtra("username",save_name);
                        intent.putExtra("usernickname",save_nickname);
                        setResult(2, intent);
                    }
                }
        );

        Button community_accept = (Button) findViewById(R.id.community_accept);
        community_accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        head = communityhead.getText().toString();
                        body = communityacc.getText().toString();


                        Intent intent = new Intent();
                        intent.putExtra("usernickname1",save_nickname);
                        intent.putExtra("communityhead1",head);
                        intent.putExtra("communityacc1", body);
                        setResult(1,intent);
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


        Intent prior_intent = new Intent();
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        setResult(2, prior_intent);

        finish();



    }

}