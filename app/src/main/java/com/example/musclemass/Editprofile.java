package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        final EditText edit_nickname = (EditText) findViewById(R.id.edit_nickname);

        Button profile_accept = (Button) findViewById(R.id.profile_accept);
        profile_accept.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent getintent = getIntent();
                        String username = getintent.getStringExtra("username");
                        String usernickname = edit_nickname.getText().toString();


                        Intent intent = new Intent(view.getContext(), Profile.class);
                        intent.putExtra("usernickname", usernickname);
                        intent.putExtra("username", username);

                        startActivity(intent);

                        finish();
                    }
                }
        );
        Button uploadimage = (Button) findViewById(R.id.uploadimage);
        uploadimage.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(cameraintent);

                    }
                }
        );

        Button profile_cancel = (Button) findViewById(R.id.profile_cancel);
        profile_cancel.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent getintent = getIntent();
                        String username = getintent.getStringExtra("username");
                        String usernickname = getintent.getStringExtra("usernickname");

                        Intent intent = new Intent(view.getContext(), Profile.class);
                        intent.putExtra("usernickname", usernickname);
                        intent.putExtra("username", username);
                        startActivity(intent);

                        finish();
                    }
                }
        );



    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "editprofileonstart 실행완료", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "editprofileonsresume 실행완료", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "editprofileonpause 실행완료", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "editprofileonstop 실행완료", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "editprofileondestroy 실행완료", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "editprofileonrestart 실행완료", Toast.LENGTH_LONG).show();
    }
}