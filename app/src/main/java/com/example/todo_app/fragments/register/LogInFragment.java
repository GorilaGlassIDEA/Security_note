package com.example.todo_app.fragments.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todo_app.R;
import com.example.todo_app.activities.auth.RegisterActivity;
import com.example.todo_app.activities.main.MainActivity;
import com.example.todo_app.services.AuthService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class LogInFragment extends Fragment {
    Button goToCreateUser;
    Button logInButton;
    EditText passwordText;
    EditText emailText;
    Context context;
//    CreateUserFragment createUserFragment = new CreateUserFragment();
    View view;

    public LogInFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();
        //Links
        goToCreateUser = view.findViewById(R.id.goToCreateUser);
        logInButton = view.findViewById(R.id.logInButton);
        passwordText = view.findViewById(R.id.EditTextPasswordLOG_IN);
        emailText = view.findViewById(R.id.editTextEmailLOG_IN);

        goToCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateUserFragment createUserFragment = new CreateUserFragment();
                ((RegisterActivity)getActivity()).animFragment_LR(createUserFragment);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. set value
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();


                //2. check value
                if (email.isEmpty()) {
                    showMassage("Email is empty!");
                    return;
                }
                if (password.isEmpty()) {
                    showMassage("Password is empty!");
                    return;
                }

                if (email.length() <= 6){
                    showMassage("The email is too simple!");
                }
                if (!email.contains("@")){
                    showMassage("mail is not correct");
                }
                if (password.length() < 6) {
                    showMassage("The password must contain at least 6 characters!");
                    return;
                }


                //3. do with value
                AuthService.logIn(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
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
    void showMassage(String massage) {
        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show();
    }
}
