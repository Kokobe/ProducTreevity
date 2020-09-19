package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Student extends AppCompatActivity {
    private Button button4; //timer button
    private Button button6; //student stats button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimer();
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStats();
            }
        });
    }
    public void openTimer() {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
    public void openStats() {
        Intent intent = new Intent(this, StudentStats.class);
        startActivity(intent);
    }

}