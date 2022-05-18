package com.example.todo_app.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.todo_app.R;
import com.example.todo_app.activities.auth.LogInActivity;
import com.example.todo_app.activities.auth.RegisterActivity;
import com.example.todo_app.fragments.main.AccountFragment;
import com.example.todo_app.fragments.main.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    HomeFragment homeFragment = new HomeFragment();
    AccountFragment accountFragment = new AccountFragment();
    @SuppressLint("NotifyDataSetChanged")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        
        openFragment(homeFragment);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigationHome:
                        openFragment(homeFragment);
                        return true;
                    case R.id.navigationAccount:
                        openFragment(accountFragment);
                        return true;

                }

                return false;
            }
        });
    }

    void linkViews() {
        navigationView = findViewById(R.id.bottomNavigationViewMain);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
    void openFragment(Fragment fragment){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.frameLayoutCreateNote, fragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }
    public void animFragment_RL(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_anim_rl, R.anim.exit_anim_rl);
        transaction.replace(R.id.frameLatout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void overrideAnim(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }
}

