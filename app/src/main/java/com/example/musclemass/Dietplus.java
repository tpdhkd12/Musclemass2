package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.musclemass.DietAdapter.calendarDays2;

public class Dietplus extends AppCompatActivity {


    private ArrayList<Diet_item> diet_itemArrayList;
    private PlusAdapter mAdapter;




    static Diet_tag_Recycler mmAdapter;

    //tag item array//
    static ArrayList<Diet_tag_item> tag_itemArrayList;

    private String save_foodname;
    private String all_url;
    private URL url;
    private String save_carbohy;
    private String save_protein;
    private String save_fat;

    // 저장된 아이템 어레이리스트 //
    private ArrayList<Diet_tag_item> save_itemArrayList;




    //현재 접속한 유저 어레이리스트/ /
    ArrayList<Userinfo> userinfo2;

    CalendarDay days;

    private String connect_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietplus);


        days = (CalendarDay) getIntent().getParcelableExtra("select_date_diet");

        // id에 현재 접속한 id 넣어주기 //

        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser", "");
        Type type = new TypeToken<ArrayList<Userinfo>>() {
        }.getType();
        userinfo2 = gson.fromJson(json, type);

        if (userinfo2 == null) {
            userinfo2 = new ArrayList<>();
        }
        // 현재 접속한 유저의 정보를 받아옴 //
        connect_id = userinfo2.get(0).getId();


        loadData();


        //api 관련해서 연결해줄 리사이클러뷰 //
        RecyclerView dietRecyclerView = (RecyclerView) findViewById(R.id.dietplus_api);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        dietRecyclerView.setLayoutManager(mLinearLayoutManager);

        diet_itemArrayList = new ArrayList<>();
        mAdapter = new PlusAdapter(this, diet_itemArrayList);
        dietRecyclerView.setAdapter(mAdapter);




        if (tag_itemArrayList == null){
            tag_itemArrayList = new ArrayList<>();
        }



        // tag 리사이클러뷰 연결 //
        RecyclerView tagRecycler = (RecyclerView) findViewById(R.id.diet_plus_recyclerview);
        LinearLayoutManager mmLinearLayoutManager = new LinearLayoutManager(this);
        tagRecycler.setLayoutManager(mmLinearLayoutManager);


        mmAdapter = new Diet_tag_Recycler(this, tag_itemArrayList);
        tagRecycler.setAdapter(mmAdapter);


        tag_itemArrayList.clear();
        // 음식명 검색하고 버튼 눌렀을 때의 동작 일단 어레이 클리어 //
        Button diet_plus = (Button) findViewById(R.id.dietplus_add);
        diet_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diet_itemArrayList.clear();

                EditText foodname1 = (EditText) findViewById(R.id.foodname_edit);
                save_foodname = foodname1.getText().toString();

                url = null;
                HttpURLConnection urlConn = null;
                try {
                    String fn = URLEncoder.encode(save_foodname, "UTF-8");
                    all_url = "https://openapi.foodsafetykorea.go.kr/api/116af44c28c145a19bde/I2790/json/1/10/MAKER_NAME=대표 &DESC_KOR=" + fn;
                    url = new URL(all_url);
                    NetworkTask networkTask = new NetworkTask(all_url, null);
                    networkTask.execute();
                } catch (UnsupportedEncodingException | MalformedURLException e) {
                    e.printStackTrace();
                }

                foodname1.setText("");

            }

        });


        ImageButton prior_dietplus = (ImageButton) findViewById(R.id.prior_dietplus);
        prior_dietplus.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent prior_intent = new Intent(view.getContext(), Dietrecord.class);
                        startActivity(prior_intent);
                    }
                }
        );

        // 전체 기록하고 dietrecord 리사이클러뷰에 저장해주는 버튼 //
        Button dietplus_record = (Button) findViewById(R.id.dietplus_record);
        dietplus_record.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        for (int i =0 ; i< tag_itemArrayList.size(); i++){

                            save_itemArrayList.add(tag_itemArrayList.get(i));
                        }

                        // 음식 다 추가하고 기록 할 때 나와있는 item 어레이리스트를 id와 날짜와 함께 추가 //
                        // 추가한 어레이리스트 저장 //
                        SharedPreferences preferences = getSharedPreferences("diet_record", MODE_PRIVATE);
                        SharedPreferences.Editor muscle_edit = preferences.edit();
                        Gson gson1 = new Gson();
                        String json1 = gson1.toJson(save_itemArrayList);
                        muscle_edit.putString("diet_record", json1);
                        muscle_edit.commit();

                        Intent intent = new Intent(view.getContext(), Dietrecord.class);

                        startActivity(intent);
                    }
                }
        );


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        Intent prior_intent = new Intent(this, Dietrecord.class);
        startActivity(prior_intent);

        finish();


    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("diet_record", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("diet_record", "");
        Type type = new TypeToken<ArrayList<Diet_tag_item>>() {
        }.getType();
        save_itemArrayList = gson.fromJson(json, type);

        if (save_itemArrayList == null) {
            save_itemArrayList = new ArrayList<>();
        }

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {
        private String url;
        private ContentValues values;
        TextView textView = (TextView) findViewById(R.id.textView50);

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            String result;
            // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url);
            // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.

            try {
                //전체 데이터를 제이슨 객체로 변환
                JSONObject root = new JSONObject(s);
                System.out.println("불러온 모든 정보 " + root);

                JSONObject i2790 = root.getJSONObject("I2790");
                System.out.println("i2790 빼오기 " + i2790);

                JSONArray row = i2790.getJSONArray("row");
                System.out.println("row" + row);


                for (int i = 0; i < row.length(); i++) {

                    JSONObject rowar = row.getJSONObject(i);


                    // item id//
                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    save_foodname = rowar.getString("DESC_KOR");
                    save_carbohy = rowar.getString("NUTR_CONT2");
                    save_protein = rowar.getString("NUTR_CONT3");
                    save_fat = rowar.getString("NUTR_CONT4");

                    Diet_item diet_item = new Diet_item(save_foodname, save_carbohy, save_protein, save_fat, days, connect_id, timestamp);

                    diet_itemArrayList.add(diet_item);



                }

                mAdapter.notifyDataSetChanged();

            } catch (JSONException jsonException) {

                Toast.makeText(getApplicationContext(),"해당하는 데이터가 없습니다.",Toast.LENGTH_SHORT).show();
                jsonException.printStackTrace();

            }

        }
    }
    public void check() {

        mmAdapter.notifyDataSetChanged();

    }
}