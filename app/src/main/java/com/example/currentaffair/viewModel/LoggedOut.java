package com.example.currentaffair.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.currentaffair.R;

public class LoggedOut extends AppCompatActivity {
    TextView logout_textview;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out);
        logout_textview = (TextView) findViewById(R.id.logout_textview);

        login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(LoggedOut.this, LoginUser.class);
                startActivity(login);
            }
        });
    }
}