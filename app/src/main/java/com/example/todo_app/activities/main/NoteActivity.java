package com.example.todo_app.activities.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity {
    Button createNoteButton;
    EditText titleEditText;
    EditText descriptionEditText;
    public static Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        linkedViews();


//




        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                note = new Note(
                        titleEditText.getText().toString(),
                        descriptionEditText.getText().toString()
                );
                //check text - isEmpty?
                if (titleEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "isEmpty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (descriptionEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "isEmpty", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //    private void setRefValue() {
//
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
//        String massage = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//        Toast.makeText(getBaseContext(), massage, Toast.LENGTH_SHORT).show();
//
//        DatabaseReference databaseReference = firebaseDatabase.getReference();
//
//        Query lastQuery = databaseReference.child(massage).orderByKey().limitToLast(1);
//
//        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    String exception = child.getKey();
//                    Toast.makeText(getBaseContext(), exception, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getBaseContext(), child.getValue().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                //Handle possible errors.
//            }
//        });
//    }
    private void linkedViews() {
        createNoteButton = findViewById(R.id.createNoteButton);
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
    }
}
