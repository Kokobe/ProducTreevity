package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TeacherSignUpActivity extends AppCompatActivity {
    private Button button14; //teacher log-in button
    private Button button13; //teacher sign-up button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);
        button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTeacherSignUp();
            }
        });
        button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTeacherLogIn();
            }
        });
    }
    public void openTeacherSignUp() {
        Intent intent = new Intent(this, TeacherLogInActivity.class);
        startActivity(intent);
    }
    public void openTeacherLogIn() {
        Intent intent = new Intent(this, TeacherLogInActivity.class);
        startActivity(intent);
    }
}