package com.example.currentaffair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

public class Launcher extends AppCompatActivity {
    NewsDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent loggedOut = new Intent(Launcher.this, LoginUser.class);
                        startActivity(loggedOut);
                    }
                }, 2000);

    }
}