package com.example.todo_app.fragments.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todo_app.R;
import com.example.todo_app.activities.auth.LogInActivity;
import com.example.todo_app.activities.auth.RegisterActivity;
import com.example.todo_app.activities.main.MainActivity;
import com.example.todo_app.fragments.main.HomeFragment;
import com.example.todo_app.services.AuthService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class CreateUserFragment extends Fragment {
    View view;
    Button CreateUser;
    Button logInButton;
    EditText passwordText;
    EditText emailText;



    public CreateUserFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_user, container, false);
        CreateUser = view.findViewById(R.id.buttonCreateUserCREATE_USER);
        logInButton = view.findViewById(R.id.buttonLoginCREATE_USER);
        passwordText = view.findViewById(R.id.EditTextPasswordCREATE_USER);
        emailText = view.findViewById(R.id.EditTextEmailCREATE_USER);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInFragment logInFragment = new LogInFragment();
                ((RegisterActivity)getActivity()).animFragment_LR(logInFragment);
            }
        });
        CreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. set value
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                //2. check value
                if (email.isEmpty()) {
                    showMassage("Email is empty");
                    return;
                }
                if (!email.contains("@") || email.length() < 3) {
                    showMassage("Email must be correct");
                    return;
                }
                if (password.isEmpty()) {
                    showMassage("Password is empty");
                    return;
                }
                if (password.length() < 6) {
                    showMassage("Password must be correct");
                    return;
                }


                AuthService.createUser(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                HomeFragment homeFragment = new HomeFragment();
//                                ((RegisterActivity)getActivity()).animFragment_RL(homeFragment);
                                ((RegisterActivity)getActivity()).overrideAnim(new Intent(getContext(), MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMassage("Can't create user");
                    }
                });
            }
        });
        return view;
    }
    void showMassage(String str){
        Toast.makeText(view.getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
