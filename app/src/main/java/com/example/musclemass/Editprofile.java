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
}