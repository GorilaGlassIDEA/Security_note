package com.example.todo_app.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todo_app.R;
import com.example.todo_app.activities.auth.RegisterActivity;
import com.example.todo_app.activities.main.MainActivity;
import com.example.todo_app.services.AuthService;

public class PresentSplashActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AuthService.isCreateUser()){
            intent = new Intent(getBaseContext(), MainActivity.class);

        }else {
            intent = new Intent(getBaseContext(), RegisterActivity.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentM = intent;
                startActivity(intentM);
                finish();
            }
        }, 1000);
    }
}
