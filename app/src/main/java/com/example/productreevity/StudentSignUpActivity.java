package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StudentSignUpActivity extends AppCompatActivity {

    private Button button11; //student log-in button
    private Button button10; //student sign-up button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudLogIn();
            }
        });
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudSignUp();
            }
        });
    }
    public void openStudLogIn() {
        Intent intent = new Intent(this, StudentLogInActivity.class);
        startActivity(intent);
    }
    public void openStudSignUp() {
        Intent intent = new Intent(this, StudentLogInActivity.class);
        startActivity(intent);
    }
}