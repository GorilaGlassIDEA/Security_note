package com.example.todo_app.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class User implements Serializable {
    public String password;
    public String email;
    public String id;

    public User(){}

    public User(String password, String email) {
        this.password = password;
        this.email = email;
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
