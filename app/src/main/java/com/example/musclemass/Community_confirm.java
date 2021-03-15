package com.example.musclemass;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Community_confirm extends AppCompatActivity {

    private String confirm_head1;
    private String confirm_body1;
    private String confirm_nickname1;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.community_confirm);

        TextView confirm_head = (TextView) findViewById(R.id.cmcf_head);
        TextView confirm_body = (TextView) findViewById(R.id.cmcf_body);
        TextView confirm_nickname = (TextView) findViewById(R.id.cmcf_nickname);
        ImageButton prior_cmcf = (ImageButton) findViewById(R.id.prior_cmcf);

        Intent getintent = getIntent();
        confirm_head1 = getintent.getStringExtra("communityhead1");
        confirm_body1 = getintent.getStringExtra("communityacc1");
        confirm_nickname1 = getintent.getStringExtra("usernickname1");

        confirm_head.setText(confirm_head1);
        confirm_body.setText(confirm_body1);
        confirm_nickname.setText(confirm_nickname1);

        prior_cmcf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Community.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
