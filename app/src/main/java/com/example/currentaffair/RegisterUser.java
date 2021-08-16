package com.example.currentaffair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    Button reg_submit;
    EditText user_email, user_password, confirm_password;
    TextView wrong_inputs;
    boolean state = false;
    NewsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        database = new NewsDatabase(RegisterUser.this);
        reg_submit = (Button) findViewById(R.id.reg_submit);
        user_email = (EditText) findViewById(R.id.user_email);
        user_password = (EditText) findViewById(R.id.user_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        wrong_inputs = (TextView) findViewById(R.id.wrong_inputs);

        reg_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_email.getText().toString().isEmpty() && user_password.getText().toString().isEmpty() && confirm_password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all inputs", Toast.LENGTH_SHORT).show();

                }else {

                    if (!user_password.getText().toString().equals(confirm_password.getText().toString())) {
                        wrong_inputs.setText("Passwords does not match");
                    }else {
                        registerUser(user_email.getText().toString(), user_password.getText().toString());
                    }


                }

            }
        });
    }

    public void registerUser(String email, String password){

        boolean result = database.insertUser(email, password);

        if (result == true){
            Toast.makeText(getApplicationContext(),"Account created successfully", Toast.LENGTH_SHORT).show();
            user_email.setText("");
            user_password.setText("");
            confirm_password.setText("");
            wrong_inputs.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginIntent = new Intent(RegisterUser.this, LoginUser.class);
                    startActivity(loginIntent);
                }
            },2000);

        }else {
            Toast.makeText(getApplicationContext(),"Failed to create account", Toast.LENGTH_SHORT).show();
        }

    }
}