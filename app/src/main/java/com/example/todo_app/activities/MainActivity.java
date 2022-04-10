package com.example.todo_app.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.todo_app.R;
import com.example.todo_app.adapters.NoteAdapter;
import com.example.todo_app.auth.LogInActivity;
import com.example.todo_app.models.Note;
import com.example.todo_app.models.User;
import com.example.todo_app.services.AuthService;
import com.example.todo_app.services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {
    NoteAdapter adapter;
    FloatingActionButton ExitButton;
    FloatingActionButton  button;
    RecyclerView recyclerView;
    String key;
    @SuppressLint("NotifyDataSetChanged")
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthService.exit();
                startActivity(new Intent(getBaseContext(), LogInActivity.class));
            }
        });



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");

        adapter = new NoteAdapter(DatabaseService.getUserOptions());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseService.createNewNote();
//                startActivity(new Intent(getBaseContext(), NoteActivity.class));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }



    void linkViews() {
        recyclerView = findViewById(R.id.RecyclerView);
        button = findViewById(R.id.floatingActionButton);
        ExitButton = findViewById(R.id.ExitButton);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

