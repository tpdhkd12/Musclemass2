
package com.example.musclemass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    private String username;
    private String usernickname;
    private String userpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        usernickname = intent.getStringExtra("usernickname");
        userpassword = intent.getStringExtra("password");



        final TextView name_profile = (TextView) findViewById(R.id.name_profile);
        name_profile.setText(username);
        final TextView nickname_profile = (TextView) findViewById(R.id.nickname_profile);
        nickname_profile.setText(usernickname);


        ImageButton prior_profile = (ImageButton) findViewById(R.id.prior_profile);
        prior_profile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                        Intent prior_intent = new Intent(view.getContext(), Choice.class);
                        prior_intent.putExtra("username",username);
                        prior_intent.putExtra("usernickname",usernickname);

                        startActivity(prior_intent);

                        finish();
                    }
                }
        );

        Button editprofile = (Button) findViewById(R.id.editprofile);
        editprofile.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Editprofile.class);
                        intent.putExtra("username", username);
                        intent.putExtra("usernickname", usernickname);
                        startActivity(intent);
                    }
                }
        );

        Button changepassword = (Button) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Changepassword.class);
                        startActivity(intent);
                    }
                }
        );

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );

        Button withdrawal = (Button) findViewById(R.id.withdrawal);
        withdrawal.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );




    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();


        Intent prior_intent = new Intent(this, Choice.class);
        prior_intent.putExtra("username",username);
        prior_intent.putExtra("usernickname",usernickname);

        startActivity(prior_intent);
        finish();



    }

}