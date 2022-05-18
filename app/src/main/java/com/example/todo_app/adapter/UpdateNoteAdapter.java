package com.example.todo_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.activities.main.ExtendsNoteActivity;
import com.example.todo_app.models.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class UpdateNoteAdapter extends FirebaseRecyclerAdapter<Note, UpdateNoteAdapter.NoteViewHolder> implements Serializable {
    public UpdateNoteAdapter(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.bind(note);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disign_note, parent, false);
        Context context = parent.getContext();
        return new UpdateNoteAdapter.NoteViewHolder(view);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder implements Serializable {

        Button deleteButton;
        Button editButton;
        TextView dateTextView;
        TextView titleTextView;
        TextView descriptionTextView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            editButton = itemView.findViewById(R.id.editButton);
        }

        public void bind(Note note) {
            titleTextView.setText(note.title);
            descriptionTextView.setText(note.description);
            dateTextView.setText(note.date);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeValue(note);
                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeValue(note);
                    createValue(note);
                    Intent i  = new Intent(v.getContext(), ExtendsNoteActivity.class);
                    v.getContext().startActivity(i);
                }
            });
        }
        public void removeValue(Note note){
            FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(note.getId())
                    .removeValue();
        }
        public void createValue(Note note){
            FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .push()
                    .setValue(note);
        }
    }
}
