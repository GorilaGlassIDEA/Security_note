package com.example.todo_app.fragments.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todo_app.R;
import com.example.todo_app.activities.auth.LogInActivity;
import com.example.todo_app.activities.auth.RegisterActivity;
import com.example.todo_app.activities.main.MainActivity;
import com.example.todo_app.services.AuthService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.util.Random;

public class AccountFragment extends Fragment {
    Button ExitButton;
    View viewGender;
    TextView nameAcc;
    TextView creationDate;
    TextView email;
    Handler mHandler = new Handler();

    public AccountFragment(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_account, container,false);
        ExitButton = view.findViewById(R.id.ExitButton);

        viewGender = view.findViewById(R.id.genderView);
//        if ((new Date().getSeconds())%2 == 0){
//            viewGender.setBackgroundResource(R.drawable.icon_woman);
//        }else {viewGender.setBackgroundResource(R.drawable.icon_man);}

        nameAcc = view.findViewById(R.id.nameAcc);
        nameAcc.setText("*" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        creationDate = view.findViewById(R.id.dateTextViewAcc);
        email = view.findViewById(R.id.emailTextViewAcc);
        email.setText("Email: "+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        viewGender.setBackgroundResource(R.drawable.icon_woman);
        int[] images = {R.drawable.icon_woman, R.drawable.icon_man,R.drawable.icon_man3,R.drawable.icon_woman2};

        Toast.makeText(view.getContext(), "Click avatar!", Toast.LENGTH_SHORT).show();
        viewGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ran = new Random();
                viewGender.setBackgroundResource(images[ran.nextInt(images.length)]);
            }
        });


        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthService.exit();
                ((MainActivity)getActivity()).overrideAnim(new Intent(getContext(), RegisterActivity.class));
            }

        });
        return view;
    }

}
