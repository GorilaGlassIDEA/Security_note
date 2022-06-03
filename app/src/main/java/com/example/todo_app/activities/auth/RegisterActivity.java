package com.example.todo_app.activities.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todo_app.R;

import com.example.todo_app.fragments.register.LogInFragment;

public class RegisterActivity extends AppCompatActivity {

        LogInFragment logInFragment = new LogInFragment();
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

        openFragment(logInFragment);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLatout, fragment);
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
        public void openFragment(FragmentTransaction transaction){
            transaction.addToBackStack(null);
            transaction.commit();
        }
        public void animFragment_LR(Fragment fragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
            transaction.replace(R.id.frameLatout, fragment);
            openFragment(transaction);
        }
        public void overrideAnim(Intent intent){
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        }
}
