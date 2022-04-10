package com.example.todo_app.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class GetFirebaseUser implements Serializable {

    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
    static DatabaseReference ref;

//    public static void str(String key) {
///*            ref = firebaseDatabase.getReference().child(key);
//            ref.setValue(key);
////            intent.putExtra("PASS", (Serializable) key);*/
//    }
}
