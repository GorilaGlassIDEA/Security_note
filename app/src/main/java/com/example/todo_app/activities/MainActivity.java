package com.example.todo_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo_app.R;
import com.example.todo_app.adapters.newAdapter.UpdateNoteAdapter;
import com.example.todo_app.adapters.old.NoteAdapter;
import com.example.todo_app.auth.LogInActivity;
import com.example.todo_app.decoration.SpacesItemDecoration;
import com.example.todo_app.services.AuthService;
import com.example.todo_app.services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    UpdateNoteAdapter adapter;
    FloatingActionButton ExitButton;
    FloatingActionButton  button;
    RecyclerView recyclerView;
    Button showButton;
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

        adapter = new UpdateNoteAdapter(DatabaseService.getUserOptions());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatabaseService.createNewNote();
                startActivity(new Intent(getBaseContext(), NoteActivity.class));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //note decoration
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
//        ItemTouchHelper.SimpleCallback itemTouchSimpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };

    }



    void linkViews() {
        recyclerView = findViewById(R.id.RecyclerView);
        button = findViewById(R.id.floatingActionButton);
        ExitButton = findViewById(R.id.ExitButton);
        showButton = findViewById(R.id.showButton);
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

