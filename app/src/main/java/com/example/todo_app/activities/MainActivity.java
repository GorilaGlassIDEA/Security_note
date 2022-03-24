package com.example.todo_app.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.todo_app.R;
import com.example.todo_app.adapters.NoteAdapter;
import com.example.todo_app.auth.LogInActivity;
import com.example.todo_app.models.Note;
import com.example.todo_app.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    NoteAdapter adapter;
    FloatingActionButton ExitButton;
    ArrayList<Note> notes = new ArrayList<>();
    ArrayList<Note> newNotes = new ArrayList<>();

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

        notes.add(new Note(
                "Новая записка",
                "djahs dhas jhda js",
                new Date().getHours() + ":" + new Date().getMinutes(),
                false
        ));
        databaseValue();
        FloatingActionButton button = findViewById(R.id.floatingActionButton);

        newNotes.add(notes.get(0));

        adapter = new NoteAdapter(newNotes);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), NoteActivity.class));
            }
        });
    }



    void linkViews() {
        ExitButton = findViewById(R.id.ExitButton);

    }
    private void databaseValue() {
        DatabaseReference contactsList = FirebaseDatabase.getInstance().getReference("Note data");
        contactsList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Note> t = new GenericTypeIndicator<Note>() {};
                notes.add(dataSnapshot.child("Note data").getValue(t));
                System.out.println(dataSnapshot.child("Note data").getValue(t));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

