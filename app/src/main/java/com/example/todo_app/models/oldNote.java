package com.example.todo_app.models;

import java.io.Serializable;

public class oldNote implements Serializable {
    public String title;
    private String description; // nullable
    public String timeCreated;
    public boolean isChecked;

    public oldNote() {
    }

    public oldNote(String title, String description, String timeCreated, boolean isChecked) {
        this.title = title;
        this.description = description;
        this.timeCreated = timeCreated;
        this.isChecked = isChecked;
    }



    public String getDescription() {
        if (description == null) {
            return "";
        } else {
            return description;
        }
    }

}
