package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StudBreakActivity extends AppCompatActivity {
    private Button button8; //break 1
    private Button button9; //break 2
    private Button button13; //break 3
    private Button button14; //break 4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_break);
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak1();
            }
        });
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak2();
            }
        });
        button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak3();
            }
        });
        button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak4();
            }
        });
    }
    public void openBreak1() {
        Intent intent = new Intent(this, Break1Activity.class);
        startActivity(intent);
    }
    public void openBreak2() {
        Intent intent = new Intent(this, Break2Activity.class);
        startActivity(intent);
    }
    public void openBreak3() {
        Intent intent = new Intent(this, Break3Activity.class);
        startActivity(intent);
    }
    public void openBreak4() {
        Intent intent = new Intent(this, Break4Activity.class);
        startActivity(intent);
    }
}