package com.example.todo_app.adapters.newAdapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Objects;

public class UpdateNoteAdapter extends FirebaseRecyclerAdapter<Note, UpdateNoteAdapter.NoteViewHolder> {
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

        return new UpdateNoteAdapter.NoteViewHolder(view);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {

        Button showButton;
        TextView dateTextView;
        TextView titleTextView;
        TextView descriptionTextView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            showButton = itemView.findViewById(R.id.showButton);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }




        public void bind(Note note) {
            titleTextView.setText(note.title);
            descriptionTextView.setText(note.description);
            dateTextView.setText(note.date);


        }
    }
}
