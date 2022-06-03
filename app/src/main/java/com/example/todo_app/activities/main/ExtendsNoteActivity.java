package com.example.todo_app.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ExtendsNoteActivity extends NoteActivity {
    public Note note;
    public Button deleteNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        linkedViews();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference ref = firebaseDatabase.getReference(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("note");
        Query lastQuery = ref.orderByKey().limitToLast(1);


        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    note = child.getValue(Note.class);
                    removeValue(child.getKey());
                    assert note != null;
                    titleEditText.setText(note.title);
                    descriptionEditText.setText(note.description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Handle possible errors.
            }
        });
        createNoteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                note = new Note(
                        titleEditText.getText().toString(),
                        descriptionEditText.getText().toString()
                );
                //check text - isEmpty?
                if (titleEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Name note - empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (descriptionEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Description - empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                //note = new Note(titleEditText.getText().toString(),  descriptionEditText.getText().toString(),  new Date().getHours() + ":" + new Date().getMinutes());
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
                DatabaseReference ref = firebaseDatabase.getReference(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("note").push();
                ref.setValue(note);
                startActivity(i);
            }
        });
        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeValue(note.getId());
                Toast.makeText(getBaseContext(), "note Delete!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });

    }
    private void linkedViews() {
        createNoteButton = findViewById(R.id.createNoteButton);
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        deleteNoteButton = findViewById(R.id.deleteNoteButton);
    }

    public void removeValue(String id) {
        FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("note")
                .child(id)
                .removeValue();
    }
}
