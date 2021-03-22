
package com.example.musclemass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {


    private String id;
    private String pw;
    private String name;
    private String nickname;



    // 현재 접속한 유저 어레이리스트 //
    ArrayList<Userinfo> userinfo;

    // 전체 유저의 어레이 리스트 //
    ArrayList<Userinfo> saveinfo;

    // id 와 사진을 비교해줄 어레이리스트 //
    ArrayList<ID_image> id_images;

    // 저장한 이미지를 불러오기 위한 어레이 리스트 //
    ArrayList<ID_image> save_id_image;


    Uri imageuri;
    ImageView profile_image ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        loadData();

        // 전체 접속한 유저의 어레이리스트에 쉐어드의 내용을 담아줌 //
        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("register","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        saveinfo = gson.fromJson(json, type);

        if (saveinfo == null){
            saveinfo = new ArrayList<>();
        }

        // 현재 접속한 유저의 정보를 받아옴 //
        id = userinfo.get(0).getId();
        pw = userinfo.get(0).getPw();
        name = userinfo.get(0).getName();
        nickname = userinfo.get(0).getNickname();


        // id를 비교해서 맞는 아이디의 image source를 찾아서 프로필에 보여줌 //
        if (id_images == null) {
            id_images = new ArrayList<>();
        }
        SharedPreferences sp2 = getSharedPreferences("profile_image", MODE_PRIVATE);
        Gson gson1 = new Gson();
        String json1 = sp2.getString("profile_image","");
        Type type1 = new TypeToken<ArrayList<ID_image>>() {}.getType();
        id_images = gson1.fromJson(json1, type1);

        for (int i = 0; i<id_images.size(); i++){

            if (id.equals(id_images.get(i).getId())) {
                    String image = id_images.get(i).getImage();

                    imageuri = Uri.parse(image);

                    profile_image = (ImageView) findViewById(R.id.profile_image);


                    // for문으로 아이디와 같은 값을 가진 사진만 넣어주면 회원마다 프로필 이미지가 달라짐 //
                    Glide.with(this).asBitmap().load(imageuri).into(profile_image);
            }
        }

        final TextView name_profile = (TextView) findViewById(R.id.name_profile);
        final TextView nickname_profile = (TextView) findViewById(R.id.nickname_profile);

      //이름 닉네임 settext 설정해줘야함 //

        name_profile.setText(name);
        nickname_profile.setText(nickname);

        ImageButton prior_profile = (ImageButton) findViewById(R.id.prior_profile);
        prior_profile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        Intent prior_intent = new Intent(view.getContext(), Choice.class);
                        startActivity(prior_intent);
                        finish();
                    }
                }
        );

        // 프로필 수정 버튼으로 이동 //
        Button editprofile = (Button) findViewById(R.id.editprofile);
        editprofile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Editprofile.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );


        // 비밀번호 수정 버튼으로 이동 //
        Button changepassword = (Button) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Changepassword.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        // 로그아웃 버튼 //
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Home.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        // 회원탈퇴 버튼 //
        Button withdrawal = (Button) findViewById(R.id.withdrawal);
        withdrawal.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Home.class);
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
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null){
            userinfo = new ArrayList<>();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();

        // 이 유저의 정보의 이미지를 저장해줘야함 //


    }
    private void saveData() {

        for (int i = 0; i<saveinfo.size(); i++){
            if (id.equals(saveinfo.get(i).getId())){

                //이 객체를 전체 유저정보에 담아줄 예정 //
                Userinfo user1 = new Userinfo(id,pw,name,nickname);

                // 전체 유저정보의 index에 맞는 정보들을 이제 바꿔줌 //
                saveinfo.set(i,user1);

                // 데이터 다시 저장 //
                SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(saveinfo);
                editor.putString("register", json);
                editor.commit();


            }
        }

    }
}