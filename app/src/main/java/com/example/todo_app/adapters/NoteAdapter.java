package com.example.todo_app.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.models.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NoteAdapter extends FirebaseRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {
    public NoteAdapter(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewCreated;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCreated = itemView.findViewById(R.id.textViewCreated);
        }

        public void bind(Note note) {
            textViewTitle.setText(note.title);
            textViewDescription.setText(note.getDescription());
            textViewCreated.setText(note.timeCreated);
        }
    }
}
//public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
//    public ArrayList<Note> notes;
//
//    public NoteAdapter(ArrayList<Note> notes) {
//        this.notes = notes;
//    }
//
//    @NonNull
//    @Override
//    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_note, parent, false);
//        return new NoteViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
//        Note note = notes.get(position);
//        holder.bind(note);
//    }
//
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//    static class NoteViewHolder extends RecyclerView.ViewHolder {
//        TextView titleTextView;
//        TextView descriptionTextView;
//        TextView createdTextView;
//        CheckBox checkBox;
//
//        public NoteViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.textViewTitle);
//            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
//            createdTextView = itemView.findViewById(R.id.textViewCreated);
//            checkBox = itemView.findViewById(R.id.checkBox);
//        }
//
//        public void bind(Note note) {
//            titleTextView.setText(note.title);
//            descriptionTextView.setText(note.getDescription());
//            createdTextView.setText(note.timeCreated);
//            checkBox.setChecked(note.isChecked);
//
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    note.isChecked = b;
//                }
//            });
//        }
//    }
//}

