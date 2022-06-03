package com.example.todo_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Objects;

public class NoteAdapterRecover extends FirebaseRecyclerAdapter<Note, NoteAdapterRecover.NoteViewHolder> implements Serializable {
    public NoteAdapterRecover(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.bind(note);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disign_note_recover, parent, false);
        return new NoteAdapterRecover.NoteViewHolder(view);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {

        Button recoverButton;
        TextView titleTextView;
        TextView dateTextView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            recoverButton = itemView.findViewById(R.id.recoverButtonRecover);
            titleTextView = itemView.findViewById(R.id.titleTextViewRecover);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        public void bind(Note note) {
            titleTextView.setText(note.title);
            dateTextView.setText(note.date);
            recoverButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Recover note!", Toast.LENGTH_SHORT).show();
                    createValue(note);
                    removeValue(note);
                }
            });
        }


        public void createValue(Note note) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
            DatabaseReference ref = firebaseDatabase.getReference(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("note").push();
            ref.setValue(note);
        }
        public void removeValue(Note note){
            FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("archive")
                    .child(note.getId())
                    .removeValue();
        }
    }
}

