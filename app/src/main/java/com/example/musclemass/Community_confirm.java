package com.example.musclemass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Community_confirm extends AppCompatActivity {


    TextView confirm_head;
    TextView confirm_body;
    TextView confirm_nickname1;
    ImageButton prior_cmcf;

    ImageView cmcf_profile;
    ImageView comment_community;

    private Uri imageuri;

    private String community_itemid;
    private String community_userid;
    private String connect_userid;
    private String connect_user_nick;
    private String uri;
    private String postid;
    EditText comment;

    private String text;

    Comment_Adapter mAdapter;
    RecyclerView recyclerView;


    private ArrayList<Communityitem> load_item;

    private ArrayList<Userinfo> connect_user;

    private ArrayList<ID_image> id_images;

    private ArrayList<CommentItem> commentItems;
    private ArrayList<CommentItem> save_itemArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.community_confirm);


        confirm_head = (TextView) findViewById(R.id.cmcf_head);
        confirm_body = (TextView) findViewById(R.id.cmcf_body);
        confirm_nickname1 = (TextView) findViewById(R.id.cmcf_nickname);
        prior_cmcf = (ImageButton) findViewById(R.id.prior_cmcf);
        cmcf_profile = (ImageView) findViewById(R.id.cmcf_profile);
        comment_community = (ImageView) findViewById(R.id.comment_userid);
        recyclerView = findViewById(R.id.comment_recycler);



        // item id??? ???????????? ??????????????? image??? set ???????????? //
        Intent getintent = getIntent();
        community_itemid = getintent.getStringExtra("community_itemid");

        // ?????? ????????? ????????? itemid??? ????????? id ??? ????????? ????????? ?????? set ????????? //
        SharedPreferences sharedPreferences = getSharedPreferences("community_item", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("community_item", "");
        Type type = new TypeToken<ArrayList<Communityitem>>() {
        }.getType();
        load_item = gson.fromJson(json, type);

        // loaditem ????????????????????? ?????? item?????? ???????????? //
        if (load_item == null) {
            load_item = new ArrayList<>();
        }

        for (int i = 0; i < load_item.size(); i++) {
            if (load_item.get(i).getTimestamp().equals(community_itemid)) {

                postid = load_item.get(i).getTimestamp();
                confirm_head.setText(load_item.get(i).getTitleStr());
                confirm_body.setText(load_item.get(i).getDescStr());
                confirm_nickname1.setText(load_item.get(i).getNickname());
                community_userid = load_item.get(i).getIdStr();

            }

        }
        // ?????? ?????? ????????? load //
        loadData();

        for (int i =0; i<save_itemArrayList.size(); i++){
            if (postid.equals(save_itemArrayList.get(i).getPostid())){
                if (commentItems == null) {
                    commentItems = new ArrayList<>();
                }
                commentItems.add(save_itemArrayList.get(i));

            }
        }


        // ???????????? ???????????? ?????????????????? ???????????? ???????????? ????????? uri ?????? //
        SharedPreferences sharedPreferences2 = getSharedPreferences("profile_image", MODE_PRIVATE);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences2.getString("profile_image", "");
        Type type2 = new TypeToken<ArrayList<ID_image>>() {
        }.getType();
        id_images = gson2.fromJson(json2, type2);

        if (id_images == null) {
            id_images = new ArrayList<>();
        }

        // ????????? ????????? ???????????? ??? ???????????? ?????? ????????? ?????? ?????? ??????????????? ?????? //
        for (int i = 0; i < id_images.size(); i++) {
            if (community_userid.equals(id_images.get(i).getId())) {
                String image = id_images.get(i).getImage();

                imageuri = Uri.parse(image);

                Glide.with(this).asBitmap().load(imageuri).into(cmcf_profile);

            }
        }



        // ?????? ????????? ????????? ???????????? ???????????? ????????? ??? ??? ????????? ??? ??? //
        SharedPreferences sharedPreferences3 = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson3 = new Gson();
        String json3 = sharedPreferences3.getString("connectuser", "");
        Type type3 = new TypeToken<ArrayList<Userinfo>>() {
        }.getType();
        connect_user = gson3.fromJson(json3, type3);

        if (connect_user == null) {
            connect_user = new ArrayList<>();
        }

        connect_userid = connect_user.get(0).getId();
        connect_user_nick = connect_user.get(0).getNickname();


        // ????????? ????????? ???????????? ??? ???????????? ?????? ????????? ?????? ?????? ??????????????? ?????? //
        for (int i = 0; i < id_images.size(); i++) {
            if (connect_userid.equals(id_images.get(i).getId())) {
                String image = id_images.get(i).getImage();

                Uri uri = Uri.parse(image);

                Glide.with(this).asBitmap().load(uri).into(comment_community);

            }
        }

        for (int i = 0; i < id_images.size(); i++) {
            if (connect_userid.equals(id_images.get(i).getId())) {
                uri = id_images.get(i).getImage();

            }
        }


        //?????????????????? ????????? ?????? //

        mAdapter = new Comment_Adapter(this, commentItems);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.notifyDataSetChanged();



        // ?????? ????????? ???????????? ?????? ?????? ?????? ??? ??? //
        ImageButton commentsend = (ImageButton) findViewById(R.id.comment_send);

        commentsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ??? ????????? ????????? ???????????? ????????????????????? ????????????, ??????????????? ?????? ?????????????????? //
                // ???????????? ???????????? //
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("hh:mm");
                String getTime = simpleDate.format(date);

                comment = (EditText) findViewById(R.id.comment_text);

                for (int i = 0; i<id_images.size(); i++){
                    if (connect_userid.equals(id_images.get(i).getId())){
                        uri = id_images.get(i).getImage();

                    }
                }

                if(commentItems == null){
                    commentItems = new ArrayList<>();
                }

                text = comment.getText().toString();


                CommentItem commentItem1 = new CommentItem(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()),getTime,connect_userid,text,connect_user_nick,uri,postid);

                commentItems.add(commentItem1);
                save_itemArrayList.add(commentItem1);

                comment.setText("");
                mAdapter = new Comment_Adapter(getApplicationContext(), commentItems);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mAdapter.notifyDataSetChanged();

                 //????????? ???????????? ?????? ??????????????? ?????????????????? //
                saveData();


            }
        });


        prior_cmcf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Community.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("comment_item", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("comment_item", "");
        Type type = new TypeToken<ArrayList<CommentItem>>() {
        }.getType();
        save_itemArrayList = gson.fromJson(json, type);

        if (save_itemArrayList == null) {
            save_itemArrayList = new ArrayList<>();
        }

    }

    private void saveData() {

        // ????????? ?????????????????? ?????? //
        SharedPreferences preferences = getSharedPreferences("comment_item", MODE_PRIVATE);
        SharedPreferences.Editor muscle_edit = preferences.edit();
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(save_itemArrayList);
        muscle_edit.putString("comment_item", json1);
        muscle_edit.commit();

        if (save_itemArrayList == null) {
            save_itemArrayList = new ArrayList<>();
        }

    }
}
