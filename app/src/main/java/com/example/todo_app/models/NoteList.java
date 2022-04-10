package com.example.todo_app.models;

public class NoteList {

    public Note note;
    NoteList(){}

    public NoteList( Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteList{" +
                ", note=" + note +
                '}';
    }
}
