package com.example.todo_app.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo_app.activities.MainActivity;
import com.example.todo_app.R;
import com.example.todo_app.services.AuthService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class CreateUserActivity extends AppCompatActivity{
    Button CreateUser;
    Button logInButton;
    EditText passwordText;
    EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        linkedViews(); // linked layout

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LogInActivity.class));
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
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMassage("Can't create user");
                    }
                });
            }
        });

    }

    void linkedViews() {
        CreateUser = findViewById(R.id.buttonCreateUserCREATE_USER);
        logInButton = findViewById(R.id.buttonLoginCREATE_USER);
        passwordText = findViewById(R.id.EditTextPasswordCREATE_USER);
        emailText = findViewById(R.id.EditTextEmailCREATE_USER);
    }

    void showMassage(String massage) {
        Toast.makeText(getBaseContext(), massage, Toast.LENGTH_SHORT).show();
    }
}
