package com.example.todo_app.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.adapter.NoteAdapterRecover;
import com.example.todo_app.models.Note;
import com.example.todo_app.services.DatabaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ArchiveFragment extends Fragment {
    NoteAdapterRecover adapter;
    RecyclerView recyclerView;
    View view;
    Button buttonDeleteAll;
    public ArchiveFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_archive,container,false);
        buttonDeleteAll = view.findViewById(R.id.buttonDeleteAll);
        adapter = new NoteAdapterRecover(DatabaseService.getUserOptionsRecover());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.recyclerViewRecover);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app")
                        .getReference(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("archive")
                        .removeValue();
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
}
