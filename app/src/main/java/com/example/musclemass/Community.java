package com.example.musclemass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class Community extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;
    ArrayList<Communityitem> communityitems;

    ArrayList<Communityitem> searchitem;

    ArrayList<Communityitem> myitem;


    // 쉐어드에서 connect유저의 정보 불러올 리스트 //
    private ArrayList<Userinfo> userinfos;

    private String connect_id;

    static boolean mycheck;

    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);



        // 쉐어드에 저장된 현재 접속한 유저 정보를 저장된 리사이클러뷰 데이터와 비교해줄 변수에 담아줌 //
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser", "");
        Type type = new TypeToken<ArrayList<Userinfo>>() {
        }.getType();
        userinfos = gson.fromJson(json, type);

        // 현재 접속한 유저의 정보를 받아옴 //
        connect_id = userinfos.get(0).getId();


        // 저장해놓은 리사이클러뷰 데이터 싹 다 불러와서 여기 어레이리스트에 저장 //
        loadData();

        // 리사이클러뷰와 item 연결//
        mRecyclerView = findViewById(R.id.community_recyclerview);
        mAdapter = new RecyclerImageTextAdapter(this, communityitems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.notifyDataSetChanged();

        EditText search_community = (EditText) findViewById(R.id.search_community);
        search_community.setVisibility(View.GONE);
        ImageButton community_realsearch = (ImageButton) findViewById(R.id.community_realsearch);
        community_realsearch.setVisibility(View.GONE);

        check = false;

        mycheck = false;

        Button my_btn = (Button) findViewById(R.id.my_btn);
        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check == false){

                    communityitems.clear();
                    for (int i =0; i<myitem.size(); i++){
                        if (connect_id.equals(myitem.get(i).getIdStr())){

                            communityitems.add(myitem.get(i));
                        }
                    }

                    mAdapter.notifyDataSetChanged();

                    check = !check;

                    mycheck = !mycheck;


                    Toast.makeText(getApplicationContext(),"mycheck" + mycheck,Toast.LENGTH_SHORT).show();
                }else{

                    communityitems.clear();


                    myitem.clear();

                    if (communityitems == null){
                        communityitems = new ArrayList<>();
                    }


                    SharedPreferences sharedPreferences = getSharedPreferences("community_item", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("community_item", "");
                    Type type = new TypeToken<ArrayList<Communityitem>>() {
                    }.getType();
                    myitem = gson.fromJson(json, type);

                    for (int i = 0; i<myitem.size(); i++){

                        communityitems.add(myitem.get(i));
                    }

                    mAdapter.notifyDataSetChanged();

                    mycheck = !mycheck;
                    check = !check;

                    Toast.makeText(getApplicationContext(),"mycheck" + mycheck,Toast.LENGTH_SHORT).show();


                }


            }
        });


        ImageButton community_search = (ImageButton) findViewById(R.id.community_search);
        community_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (search_community.getVisibility() == View.GONE){

                    search_community.setVisibility(View.VISIBLE);
                    community_realsearch.setVisibility(View.VISIBLE);
                }else {
                    search_community.setVisibility(View.GONE);
                    community_realsearch.setVisibility(View.GONE);

                    communityitems.clear();

                    if (communityitems == null){
                        communityitems = new ArrayList<>();
                    }
                    searchitem.clear();

                    SharedPreferences sharedPreferences = getSharedPreferences("community_item", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("community_item", "");
                    Type type = new TypeToken<ArrayList<Communityitem>>() {
                    }.getType();
                    searchitem = gson.fromJson(json, type);

                    for (int i = 0; i<searchitem.size(); i++){

                        communityitems.add(searchitem.get(i));
                    }

                    mAdapter.notifyDataSetChanged();
                }


            }
        });
        community_realsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                communityitems.clear();

                for (int i = 0; i < searchitem.size(); i++) {
                    if (searchitem.get(i).getNickname().contains(search_community.getText().toString()) || searchitem.get(i).getTitleStr().contains(search_community.getText().toString()) || searchitem.get(i).getDescStr().contains(search_community.getText().toString())) {

                        communityitems.add(searchitem.get(i));
                    }

                }

                mAdapter.notifyDataSetChanged();

                search_community.setText("");
            }
        });


        ImageButton prior_community = (ImageButton) findViewById(R.id.prior_community);
        prior_community.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent prior_intent = new Intent(v.getContext(), Choice.class);

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
                        startActivity(intent);
                        finish();
                    }
                }
        );


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        Intent prior_intent = new Intent(this, Choice.class);
        startActivity(prior_intent);
        finish();


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("community_item", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("community_item", "");
        Type type = new TypeToken<ArrayList<Communityitem>>() {
        }.getType();
        communityitems = gson.fromJson(json, type);
        searchitem = gson.fromJson(json, type);
        myitem = gson.fromJson(json,type);

        if (myitem == null){
            myitem = new ArrayList<>();
        }

        if (communityitems == null) {
            communityitems = new ArrayList<>();
        }
        if (searchitem == null) {
            searchitem = new ArrayList<>();
        }

    }


}