package com.example.todo_app.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.fragments.main.HomeFragment;
import com.example.todo_app.models.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NoteAdapterAdd extends FirebaseRecyclerAdapter<Note, NoteAdapterAdd.NoteViewHolder> {


    public void setOnCardClickListener(OnCardClickListener listener) {
        mListener = listener;
    }

    public NoteAdapterAdd(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }


    public interface OnCardClickListener {
        void onCardClick(CharSequence id, CharSequence title, CharSequence description);
    }

    // создаем поле объекта-колбэка
    private static OnCardClickListener mListener;

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.bind(note);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disign_note_add, parent, false);
        return new NoteAdapterAdd.NoteViewHolder(view);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {


        CheckBox deleteCheckBox;
        Button editButton;
        EditText titleTextView;
        EditText descriptionEditText;
        View viewOn;
        Button floatingActionButton;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            titleTextView = itemView.findViewById(R.id.titleTextViewRecover);
            floatingActionButton = itemView.findViewById(R.id.floatingActionButton);
            descriptionEditText = itemView.findViewById(R.id.descriptionTextViewRecover);
            editButton = itemView.findViewById(R.id.recoverButtonRecover);
            viewOn = itemView.findViewById(R.id.viewOn);
        }


        public void bind(Note note) {
            titleTextView.setText(note.title);
            descriptionEditText.setText(note.description);
            descriptionEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mListener.onCardClick((CharSequence) note.getId(), (CharSequence) note.title, s);
                    note.description = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            titleTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mListener.onCardClick((CharSequence) note.getId(), s,(CharSequence) note.description);
                    note.title = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            deleteCheckBox.setChecked(false);
            deleteCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeValue(note, "note");
                    createValue(note, "archive");
                }
            });

        }

        public void removeValue(Note note, String str) {
            FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(str)
                    .child(note.getId())
                    .removeValue();
        }

        public void createValue(Note note, String str) {
            FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child(str)
                    .push()
                    .setValue(note);
        }

    }
}
