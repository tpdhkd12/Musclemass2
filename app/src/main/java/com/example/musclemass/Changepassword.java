package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Changepassword extends AppCompatActivity {


    private String changepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);


        Intent getintent = getIntent();
        changepassword = getintent.getStringExtra("password");

        EditText write_password = (EditText) findViewById(R.id.write_password );
        write_password.setText(write_password.getText().toString());

        Button current_password_accept = (Button) findViewById(R.id.current_password_accept);
        current_password_accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (write_password.equals(changepassword)) {


                            Intent intent = new Intent(v.getContext(), ChangePW_change.class);
                            startActivity(intent);

                        }
                    }
                }
        );
    }
}