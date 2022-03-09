package com.example.todo_app.services;

import com.example.todo_app.models.User;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;
import java.util.UUID;

public class DatabaseService {

    public static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
    }

    public static FirebaseRecyclerOptions<User> getUserOptions() {

        Query query = getDatabase().getReference("new-users");

        ClassSnapshotParser<User> parser = new ClassSnapshotParser<>(User.class);


        return new FirebaseRecyclerOptions.Builder<User>().setQuery(query, parser).build();
    }

    public static void createRandomNewUser() {
        getDatabase().getReference("new-users").push().setValue(new User(
                UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString().substring(0, 10),
                UUID.randomUUID().toString() +
                        UUID.randomUUID().toString() +
                        UUID.randomUUID().toString(),
                new Date().getTime()
        ));
    }
}
