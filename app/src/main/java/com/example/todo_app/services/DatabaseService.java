package com.example.todo_app.services;

import android.content.Intent;

import com.example.todo_app.activities.MainActivity;
import com.example.todo_app.models.Note;
import com.example.todo_app.models.NoteList;
import com.example.todo_app.models.User;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class DatabaseService implements Serializable {

    public static FirebaseDatabase getDatabase(){
        return FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
    }

    public static Task<Void> addTestModel(String ref, User model) {
        return getDatabase()
                .getReference(ref)
                .push()
                .setValue(model);
    }

    public static void getTestModel(String ref, ValueEventListener listener) {
        getDatabase()
                .getReference(ref)
                .addListenerForSingleValueEvent(listener);
    }

    public static void getAllTestModels(String ref, ChildEventListener listener) {
        getDatabase()
                .getReference(ref)
                .orderByChild("booleanValue")
                .addChildEventListener(listener);
    }

    public static Task<Void> remove(String ref) {
        return getDatabase()
                .getReference(ref)
                .removeValue();
    }
    public static void createNewNote() {
        getDatabase()
                .getReference("notes")
                .push()
                .setValue(new Note("dfs", "sdfsf", "as", false));
    }

    public static FirebaseRecyclerOptions<Note> getUserOptions() {
        Query query = getDatabase().getReference("notes");
        ClassSnapshotParser<Note> parser = new ClassSnapshotParser<>(Note.class);

        return new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, parser)
                .build();
    }

}
