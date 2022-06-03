package com.example.todo_app.models;

import com.example.todo_app.models.intarface.IdModel;
import com.google.firebase.database.Exclude;

public class Note implements IdModel {
    public String title;
    public String description;
    public String date;
    //default constructor
    @Exclude
    private String _id;

    public Note() {
    }

    public Note(String title, String description) {
        // add/edit
        this.title = title;
        this.description = description;

    }


    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        _id = id;
    }
}