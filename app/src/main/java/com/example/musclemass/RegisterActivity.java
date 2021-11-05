package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<Userinfo> userinfo = new ArrayList<>();

    private String passwordValidation = "((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,})";


    boolean exist ;

    boolean 비번 ;

    boolean cf ;

    boolean 비번확인 ;
    String passw;
    String passw_cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loadData();


        exist=false;
        비번 = false;
        cf = false;
        비번확인 = false;

        final EditText register_id = (EditText) findViewById(R.id.register_id);
        final EditText register_pw = (EditText) findViewById(R.id.register_pw);
        final EditText register_pw_cm = (EditText) findViewById(R.id.register_pw_confirm);
        final EditText register_name = (EditText) findViewById(R.id.register_name);
        final EditText register_nickname = (EditText) findViewById(R.id.register_nickname);
        final TextView pw = (TextView) findViewById(R.id.비밀번호정규식);
        final TextView pw_cmcf = (TextView) findViewById(R.id.비밀번호확인);
        register_pw_cm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passw_cm = register_pw_cm.getText().toString().trim();
                if (passw_cm.equals(passw)&& editable.length()>0){
                    pw_cmcf.setText("비밀번호와 비밀번호확인이 일치합니다.");
                    pw_cmcf.setTextColor(Color.GREEN);
                    비번확인 = true;
                }else {
                    pw_cmcf.setText("비밀번호와 비밀번호확인이 일치하지 않습니다.");
                    비번확인= false;
                }

            }
        });

        register_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                passw = register_pw.getText().toString().trim();
                if (passw.matches(passwordValidation) && s.length()>0){
                    pw.setText("비밀번호 입력이 완료 되었습니다.");
                    pw.setTextColor(Color.GREEN);
                    비번 = true;
                }else {
                    pw.setText("정규식에 부합하지 않습니다.");
                    비번 = false;
                }

            }
        });


        Button id_confirm = (Button) findViewById(R.id.confirm_id);
        id_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cf = false;
                exist = false;
                for (int i =0; i<userinfo.size(); i++){
                    if (register_id.getText().toString().equals(userinfo.get(i).getId()) ){

                        Toast.makeText(getApplicationContext(),"사용 불가능한 아이디입니다.", LENGTH_SHORT).show();

                        exist = false;
                        cf = true;
                    }
                }


                if (cf == false){
                    Toast.makeText(getApplicationContext(),"사용 가능한 아이디입니다.", LENGTH_SHORT).show();

                    exist = true;

                }



            }
        });

        Button register_accept = (Button) findViewById(R.id.register_accept);
        register_accept.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {

                        if (exist = 비번 = 비번확인 = true) {
                            Userinfo user = new Userinfo(register_id.getText().toString(), register_pw.getText().toString(), register_name.getText().toString(), register_nickname.getText().toString());

                            userinfo.add(user);

                            saveData();

                            Intent intent = new Intent(view.getContext(), Home.class);

                            startActivity(intent);
                            finish();

                        }else if (exist = false){

                            Toast.makeText(getApplicationContext(),"아이디 중복확인을 해주세요.", LENGTH_SHORT).show();
                        }else if (비번 = false){

                            Toast.makeText(getApplicationContext(),"비밀번호를 확인 해주세요.", LENGTH_SHORT).show();


                        }else if (비번확인 = false){

                            Toast.makeText(getApplicationContext(),"비밀번호를 한번 더 입력해주세요.", LENGTH_SHORT).show();

                        }else {

                        }
                    }
                });


    }
    private void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userinfo);
        editor.putString("register", json);
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("register", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("register","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null){
            userinfo = new ArrayList<>();
        }


    }


}