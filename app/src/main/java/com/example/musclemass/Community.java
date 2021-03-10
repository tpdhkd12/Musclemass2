package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Community extends AppCompatActivity {


    String save_head;
    String save_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        TextView community_head = (TextView) findViewById(R.id.community_head);
        TextView community_text = (TextView) findViewById(R.id.community_text);

        Intent intent = getIntent();
        community_head.setText(intent.getStringExtra("communityhead"));
        community_text.setText(intent.getStringExtra("communityacc"));



        Intent getintent = getIntent();
        save_head = getintent.getStringExtra("communityhead1");
        save_text = getintent.getStringExtra("communityacc1");





        ImageButton prior_community = (ImageButton) findViewById(R.id.prior_community);
        prior_community.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");





                        Intent prior_intent = new Intent(v.getContext(), Choice.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );
        ImageButton community_write = (ImageButton) findViewById(R.id.community_write);
        community_write.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(v.getContext(), Communitywrite.class);
                        intent.putExtra("communityhead1", save_head);
                        intent.putExtra("communityacc1", save_text);
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


        Intent prior_intent = new Intent(this, Choice.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);

        finish();



    }
}