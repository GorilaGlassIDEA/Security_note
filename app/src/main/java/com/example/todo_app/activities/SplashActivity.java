package com.example.todo_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todo_app.R;
import com.example.todo_app.auth.CreateUserActivity;
import com.example.todo_app.auth.LogInActivity;
import com.example.todo_app.services.AuthService;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AuthService.isCreateUser()){
            Toast.makeText(this,"checked pas and email", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LogInActivity.class));

        }else {
            startActivity(new Intent(this, CreateUserActivity.class));
        }


    }
}