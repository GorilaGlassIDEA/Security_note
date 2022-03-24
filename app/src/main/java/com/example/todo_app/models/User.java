package com.example.todo_app.models;

import androidx.annotation.NonNull;

import java.util.Date;

public class User {
    public String name;
    public String email;
    public long creationDate;
    public String description;

    public User(){}

    public User(String name, String email,  String description, long creationDate) {
        this.name = name;
        this.email = email;
        this.creationDate =creationDate;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                '}';
    }
}
