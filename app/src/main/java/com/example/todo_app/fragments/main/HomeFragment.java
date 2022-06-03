package com.example.todo_app.fragments.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.adapter.NoteAdapterAdd;
import com.example.todo_app.adapter.NoteAdapterRecover;
import com.example.todo_app.decoration.SpacesItemDecoration;
import com.example.todo_app.models.Note;
import com.example.todo_app.services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeFragment extends Fragment implements NoteAdapterAdd.OnCardClickListener {
    NoteAdapterAdd adapter;
    RecyclerView recyclerView;
    FloatingActionButton createButton;
    Note note;
    String description;
    String title;
    String id;
    @Override
    public void onCardClick(CharSequence id, CharSequence title, CharSequence description) {
        this.description = description.toString();
        this.title = title.toString();
        this.id = id.toString();
//        Toast.makeText(getContext(),charSequence.toString(), Toast.LENGTH_LONG).show();
    }
    public HomeFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        adapter = new NoteAdapterAdd(DatabaseService.getUserOptionsAdd());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnCardClickListener(this);
        recyclerView.setAdapter(adapter);

        createButton = view.findViewById(R.id.floatingActionButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (description != null || title != null) {
                    closeKeyboard();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
                    DatabaseReference ref = firebaseDatabase.getReference(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("note");
                    note = new Note(title, description);
                    ref.child(id).setValue(note);
                    recyclerView.setAdapter(adapter);
                    description = null;
                    title = null;
                } else{
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
                    DatabaseReference ref = firebaseDatabase.getReference(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("note");
                    ref.push().setValue(new Note("Write text", "And here!"));
                }
            }

        });


        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


}
