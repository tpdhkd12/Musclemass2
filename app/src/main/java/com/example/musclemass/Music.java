package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class Music extends AppCompatActivity {


    Intent web1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=F6MI5hkLNEk"));
    Intent web2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XjTZYtbm7zs"));
    Intent web3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=aoasV0_60jY"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);


        // 프로필 정보값 받아주기 //
        Intent profile_getintent = getIntent();
        String save_name = profile_getintent.getStringExtra("username");
        String save_nickname = profile_getintent.getStringExtra("usernickname");

        ImageButton prior_music = (ImageButton) findViewById(R.id.prior_music);
        prior_music.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent prior_intent = new Intent(v.getContext(), Choice.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );

        Button music1 = (Button) findViewById(R.id.music1);
        music1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        web1.putExtra("username",save_name);
                        web1.putExtra("usernickname",save_nickname);
                        startActivity(web1);

                    }
                });

        Button music2 = (Button) findViewById(R.id.music2);
        music2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        web2.putExtra("username",save_name);
                        web2.putExtra("usernickname",save_nickname);
                        startActivity(web2);

                    }
                });
        Button music3 = (Button) findViewById(R.id.music2);
        music3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        web3.putExtra("username",save_name);
                        web3.putExtra("usernickname",save_nickname);
                        startActivity(web3);
                    }
                });

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