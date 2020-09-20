package com.example.productreevity.startAssignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.productreevity.R;
import com.example.productreevity.startAssignment.Break1Activity;
import com.example.productreevity.startAssignment.Break2Activity;
import com.example.productreevity.startAssignment.Break3Activity;
import com.example.productreevity.startAssignment.Break4Activity;

public class StudBreakActivity extends AppCompatActivity {
    private ImageView button8; //break 1
    private ImageView button9; //break 2
    private ImageView button13; //break 3
    private ImageView button14; //break 4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_break);
        button8 = (ImageView) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak1();
            }
        });
        button9 = (ImageView) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak2();
            }
        });
        button13 = (ImageView) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreak3();
            }
        });
        button14 = (ImageView) findViewById(R.id.button14);
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