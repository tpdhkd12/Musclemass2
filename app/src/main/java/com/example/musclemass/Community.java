package com.example.musclemass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Community extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null;
    ArrayList<Communityitem> mList = new ArrayList<>();

    String nickname;
    String head;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);



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

                        Intent intent = new Intent(v.getContext(), Communitywrite.class);
                        intent.putExtra("usernickname",nickname);
                        startActivityForResult(intent, 202);
                    }
                }
        );
        ImageButton community_search = (ImageButton) findViewById(R.id.community_search);
        community_search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent prior_intent = new Intent(v.getContext(), Community_confirm.class);

                        startActivity(prior_intent);

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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 202) {
            if (resultCode == 1) {

                head = data.getStringExtra("communityhead1");
                body = data.getStringExtra("communityacc1");
                nickname = data.getStringExtra("usernickname1");

                if (head != null && body !=null) {
                    mRecyclerView = findViewById(R.id.community_recyclerview);
                    mAdapter = new RecyclerImageTextAdapter(this , mList);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                    Communityitem item = new Communityitem(head, body, nickname);
                    mList.add(item);
                    mAdapter.notifyDataSetChanged();
                }



            }else if (resultCode == 2){

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}