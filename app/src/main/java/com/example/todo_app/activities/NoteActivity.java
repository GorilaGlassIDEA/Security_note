package com.example.todo_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Date;

public class NoteActivity extends AppCompatActivity implements Serializable {
    Button createNoteButton;
    EditText nameNoteEditText;
    EditText textEditText;
    //    public static ArrayList<Note> notes;
    public static Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        linkedViews();




        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note = new Note(
                        nameNoteEditText.getText().toString(),
                        textEditText.getText().toString(),
                        new Date().getHours() + ":" + new Date().getMinutes(),
                        false
                );
                //check text - isEmpty?
                if (nameNoteEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "isEmpty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (textEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "isEmpty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Firebase data
                FirebaseDatabase db = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
                DatabaseReference ref = db.getReference().child("Note data");
                ref.setValue(note);
                finish();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }

    private void linkedViews() {
        createNoteButton = findViewById(R.id.CreateNoteButton);
        nameNoteEditText = findViewById(R.id.nameNoteEditText);
        textEditText = findViewById(R.id.textEditText);
    }

}
