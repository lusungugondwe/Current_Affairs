package com.example.currentaffair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginUser extends AppCompatActivity {
    Button submit;
    EditText email, password;
    TextView registerClass, errors;
    NewsDatabase database;
    public static String successMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        database = new NewsDatabase(LoginUser.this);
        submit = (Button) findViewById(R.id.submit);
        email = (EditText) findViewById(R.id.email);
        registerClass = (TextView) findViewById(R.id.registerClass);
        errors = (TextView) findViewById(R.id.errors);
        password = (EditText) findViewById(R.id.password);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                if ( userEmail.isEmpty() && userPassword.isEmpty()){
                    errors.setText("Please enter email or password");
                }else {
                    Cursor user = database.loginUser(userEmail, userPassword);
                    if (user.getCount() == 0){
                        errors.setText("Invalid email or password combination, please try again");
                    }else {
                        database.updateUserState("logged in");
                        successMessage = "login";
                        email.setText("");
                        password.setText("");
                        Intent newsIntent = new Intent(LoginUser.this, MainActivity.class);
//                        newsIntent.putExtra("email", userEmail);
//                        newsIntent.putExtra("password", userPassword);
//                        newsIntent.putExtra("loginSuccessMessage", successMessage);
                        startActivity(newsIntent);
                    }

                }
            }
        });

        registerClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginUser.this, RegisterUser.class);
                startActivity(loginIntent);
            }
        });
    }

}