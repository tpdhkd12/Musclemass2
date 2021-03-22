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

public class Muscleplus extends AppCompatActivity {

    private ArrayList<Muscle_item> muscle_itemArrayList ;
    private MuscleAdapter mAdapter ;

    private String save_kg;
    private String save_count;
    private String save_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.muscleplus);




        RecyclerView muscleRecyclerView = (RecyclerView) findViewById(R.id.muscle_recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        muscleRecyclerView.setLayoutManager(mLinearLayoutManager);

        muscle_itemArrayList = new ArrayList<>();
        mAdapter = new MuscleAdapter(this, muscle_itemArrayList);
        muscleRecyclerView.setAdapter(mAdapter);


        Button bench_plus = (Button) findViewById(R.id.bench_add);
        bench_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText bench_kg = (EditText) findViewById(R.id.bench_kg);
                EditText bench_count = (EditText) findViewById(R.id.bench_count);
                EditText bench_set = (EditText) findViewById(R.id.bench_set);

                save_kg = bench_kg.getText().toString();
                save_count = bench_count.getText().toString();
                save_set = bench_set.getText().toString();


                Muscle_item data = new Muscle_item("벤치프레스 : "+ save_kg +" kg", "  "+save_count+" 회", " "+save_set+" 세트");

                muscle_itemArrayList.add(data);

                mAdapter.notifyDataSetChanged();


            }
        });

        Button squart_add = (Button) findViewById(R.id.squart_add);
        squart_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText squart_kg = (EditText) findViewById(R.id.squart_kg);
                EditText squart_count = (EditText) findViewById(R.id.squart_count);
                EditText squart_set = (EditText) findViewById(R.id.squart_set);

                save_kg = squart_kg.getText().toString();
                save_count = squart_count.getText().toString();
                save_set = squart_set.getText().toString();


                Muscle_item data = new Muscle_item("스쿼트 : "+ save_kg +" kg", "  "+save_count+" 회", " "+save_set+" 세트");

                muscle_itemArrayList.add(data);

                mAdapter.notifyDataSetChanged();


            }
        });

        Button dead_add = (Button) findViewById(R.id.dead_add);
        dead_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText dead_kg = (EditText) findViewById(R.id.dead_kg);
                EditText dead_count = (EditText) findViewById(R.id.dead_count);
                EditText dead_set = (EditText) findViewById(R.id.dead_set);

                save_kg = dead_kg.getText().toString();
                save_count = dead_count.getText().toString();
                save_set = dead_set.getText().toString();


                Muscle_item data = new Muscle_item("데드리프트 : "+ save_kg +" kg", "  "+save_count+" 회", " "+save_set+" 세트");

                muscle_itemArrayList.add(data);

                mAdapter.notifyDataSetChanged();


            }
        });



        ImageButton prior_muscleplus = (ImageButton) findViewById(R.id.prior_muscleplus);
        prior_muscleplus.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");


                        Intent prior_intent = new Intent(view.getContext(), Musclerecord.class);
                        prior_intent.putExtra("username",save_name);
                        prior_intent.putExtra("usernickname",save_nickname);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );

        Button muscleplus_record = (Button) findViewById(R.id.muscleplus_record);
        muscleplus_record.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent profile_getintent = getIntent();
                        String save_name = profile_getintent.getStringExtra("username");
                        String save_nickname = profile_getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(view.getContext(), Musclerecord.class);
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