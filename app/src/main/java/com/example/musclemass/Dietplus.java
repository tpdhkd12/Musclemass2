package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Dietplus extends AppCompatActivity {

    private ArrayList<Diet_item> diet_itemArrayList ;
    private DietAdapter mAdapter ;

    private String save_foodname;
    private String save_carbohy;
    private String save_protein;
    private String save_fat;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietplus);





        RecyclerView dietRecyclerView = (RecyclerView) findViewById(R.id.diet_recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        dietRecyclerView.setLayoutManager(mLinearLayoutManager);

        diet_itemArrayList = new ArrayList<>();
        mAdapter = new DietAdapter(this, diet_itemArrayList);
        dietRecyclerView.setAdapter(mAdapter);




        Button diet_plus = (Button) findViewById(R.id.dietplus_add);
        diet_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText foodname1 = (EditText) findViewById(R.id.foodname_edit);
                EditText carby1 = (EditText) findViewById(R.id.carbohydrate_edit);
                EditText protein1 = (EditText) findViewById(R.id.protein_edit);
                EditText fat1 = (EditText) findViewById(R.id.fat_edit);

                save_foodname = foodname1.getText().toString();
                save_carbohy = carby1.getText().toString();
                save_protein = protein1.getText().toString();
                save_fat = fat1.getText().toString();


                Diet_item data = new Diet_item("음식 명 : "+ save_foodname, "탄수화물 : "+save_carbohy+" g", "단백질 : "+save_protein+" g", "지방 : "+save_fat+" g");

                diet_itemArrayList.add(data);

                mAdapter.notifyDataSetChanged();


            }
        });



        ImageButton prior_dietplus = (ImageButton) findViewById(R.id.prior_dietplus);
        prior_dietplus.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent prior_intent = new Intent(view.getContext(), Dietrecord.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);
                        startActivity(prior_intent);
                    }
                }
        );

        Button dietplus_record = (Button) findViewById(R.id.dietplus_record);
        dietplus_record.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(view.getContext(), Dietrecord.class);
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


        Intent prior_intent = new Intent(this, Dietrecord.class);
        prior_intent.putExtra("username",save_name);
        prior_intent.putExtra("usernickname",save_nickname);

        startActivity(prior_intent);

        finish();



    }
}