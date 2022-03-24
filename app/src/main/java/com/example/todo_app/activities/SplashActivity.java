package com.example.todo_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todo_app.R;
import com.example.todo_app.auth.CreateUserActivity;
import com.example.todo_app.services.AuthService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AuthService.isCreateUser()){
            startActivity(new Intent(this, MainActivity.class));

        }else {
            startActivity(new Intent(this, CreateUserActivity.class));
        }


    }
}