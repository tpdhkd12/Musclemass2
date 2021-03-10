package com.example.musclemass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePW_change extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword_change);

        EditText changepwaccept = (EditText) findViewById(R.id.changepwaccept);
        EditText changepwaccept_confirm = (EditText) findViewById(R.id.changepwaccept_confirm);


        Button changepassword_accept = (Button) findViewById(R.id.changepassword_accept);
        changepassword_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changepwaccept.setText(changepwaccept.getText().toString());
                changepwaccept_confirm.setText(changepwaccept_confirm.getText().toString());

                if (changepwaccept.equals(changepwaccept_confirm)) {
                    Intent intent = new Intent(v.getContext(), Profile.class);
                    intent.putExtra("password",changepwaccept_confirm.getText());
                    startActivity(intent);
                }
            }
        });
    }
}