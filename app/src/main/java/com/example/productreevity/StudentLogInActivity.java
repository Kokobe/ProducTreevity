package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentLogInActivity extends AppCompatActivity {
    private Button button12; //student log-in button to student home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_in);
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudLogInStudHome();
            }
        });
    }
    public void openStudLogInStudHome() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}