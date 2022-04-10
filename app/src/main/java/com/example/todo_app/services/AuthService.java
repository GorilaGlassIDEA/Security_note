package com.example.todo_app.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {

    public static Task<AuthResult> logIn(String email, String password) {
        return FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password);

    }

    public static boolean isCreateUser(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static Task<AuthResult> createUser(String email, String password) {
        return FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password);
    }

    public static void exit() {
        FirebaseAuth.getInstance()
                .signOut();

    }

}
