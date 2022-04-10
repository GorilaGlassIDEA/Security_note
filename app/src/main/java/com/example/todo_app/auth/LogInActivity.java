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
import com.example.todo_app.models.User;
import com.example.todo_app.services.AuthService;
import com.example.todo_app.services.GetFirebaseUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class LogInActivity extends AppCompatActivity implements Serializable{
    Button goToCreateUser;
    Button logInButton;
    EditText passwordText;
    EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        linkedViews(); // linked layout

        goToCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateUserActivity.class));
            }
        });

//        Intent intent = new Intent(getBaseContext(),MainActivity.class);
//        intent.putExtra(GetFirebaseUser.str());


        logInButton.setOnClickListener(new View.OnClickListener() {
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


                //3. do with value
                AuthService.logIn(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://todo-app-15dcb-default-rtdb.europe-west1.firebasedatabase.app");
//                                DatabaseReference ref = firebaseDatabase.getReference().child(password);
//                                ref.setValue(password);
////                                intent.putExtra("PASS", password);
//                                intent.putExtra("PASS", (Serializable)  GetFirebaseUser.str(password));
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
        goToCreateUser = findViewById(R.id.buttonCreateUserLOG_IN);
        logInButton = findViewById(R.id.buttonlogInLOG_IN);
        passwordText = findViewById(R.id.EditTextPasswordLOG_IN);
        emailText = findViewById(R.id.EditTextEmailLOG_IN);
    }

    void showMassage(String massage) {
        Toast.makeText(getBaseContext(), massage, Toast.LENGTH_SHORT).show();
    }
}