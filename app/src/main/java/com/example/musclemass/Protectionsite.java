package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class Protectionsite extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.protectionsite);



        ImageButton prior_protectionsite = (ImageButton) findViewById(R.id.prior_protectionsite);


        prior_protectionsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_getintent = getIntent();
                String save_name = profile_getintent.getStringExtra("username");
                String save_nickname = profile_getintent.getStringExtra("usernickname");


                Intent prior_intent = new Intent(v.getContext(), Choice.class);
                prior_intent.putExtra("username",save_name);
                prior_intent.putExtra("usernickname",save_nickname);
                startActivity(prior_intent);
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


        Intent prior_intent = new Intent(this,Choice.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);


        finish();



    }


}
