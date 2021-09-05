package com.example.currentaffair.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.example.currentaffair.MainActivity;
import com.example.currentaffair.NewsDatabase;
import com.example.currentaffair.R;

public class Launcher extends AppCompatActivity {
    NewsDatabase database;
    String email = "currentaffairs-admin@gmail.com";
    String password = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        database = new NewsDatabase(Launcher.this);

        Cursor users = database.getUsers(email, password);

        if (users.getCount() == 0){
            database.insertUser(email, password);
            handle();
        }else {
            handle();
        }
    }

    public void handle(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor userState = database.getUser();
                if (userState.getCount() == 0){

                }else {
                    while (userState.moveToNext()){
                        if (userState.getString(1).toString().equals("currentaffairs-admin@gmail.com") && userState.getString(3).toString().equals("logged in")){
                            Intent loggedInAdmin = new Intent(Launcher.this, AdminControl.class);
                            startActivity(loggedInAdmin);
                        }else {
                            Intent logged = new Intent(Launcher.this, LoginUser.class);
                            startActivity(logged);
                        }
                    }
                }

            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Cursor cursor = database.getUser();
        if (cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){
                database.updateUserState("logged out", cursor.getString(1).toString());
            }
        }
    }
}