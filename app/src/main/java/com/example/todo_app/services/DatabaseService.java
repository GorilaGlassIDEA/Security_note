package com.example.todo_app.services;

import com.example.todo_app.models.Note;
import com.example.todo_app.override.ClassSnapshotParserWithID;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DatabaseService {

    public static FirebaseDatabase getDatabase(){
        return FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");

    }

    public static FirebaseRecyclerOptions<Note> getUserOptionsAdd() {
        Query query = getDatabase().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("note");
        ClassSnapshotParser<Note> parser = new ClassSnapshotParserWithID(Note.class);

        return new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, parser)
                .build();
    }
    public static FirebaseRecyclerOptions<Note> getUserOptionsRecover() {
        Query query = getDatabase().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("archive");
        ClassSnapshotParser<Note> parser = new ClassSnapshotParserWithID(Note.class);

        return new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, parser)
                .build();
    }
}
