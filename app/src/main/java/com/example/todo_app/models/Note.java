package com.example.todo_app.models;

import java.io.Serializable;

public class Note implements Serializable {
    public String title;
    private String description; // nullable
    public String timeCreated;
    public boolean isChecked;

    //    public Note(String title, String description, Date timeCreated, boolean isChecked) {
//        this.title = title;
//        this.description = description;
//        this.timeCreated = timeCreated.getHours() + ":" + timeCreated.getMinutes();
//        this.isChecked = isChecked;
//    }
    public Note() {
    }

    public Note(String title, String description, String timeCreated, boolean isChecked) {
        this.title = title;
        this.description = description;
        this.timeCreated = timeCreated;
        this.isChecked = isChecked;
    }

//    public Note(String title, Date timeCreated, boolean isChecked) {
//        this.title = title;
//        this.timeCreated = timeCreated.getHours() + ":" + timeCreated.getMinutes();
//        this.description = null;
//        this.isChecked = isChecked;
//    }


    public String getDescription() {
        if (description == null) {
            return "";
        } else {
            return description;
        }
    }

//    public String getCreatedTime() {
//        int h = timeCreated.getHours();
//        int m = timeCreated.getMinutes();
//
//        return h + ":" + m;
//    }
}
