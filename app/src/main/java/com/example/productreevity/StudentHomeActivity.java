package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentHomeActivity extends AppCompatActivity {
    private ImageView button4; //timer button
    private ImageView button6; //student stats button
    private ImageView imageView8; //student shop button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        button4 = (ImageView) findViewById(R.id.start_assignment_btn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimer();
            }
        });
        button6 = (ImageView) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStats();
            }
        });
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudShop();
            }
        });
    }
    public void openTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }
    public void openStats() {
        Intent intent = new Intent(this, StudentStatsActivity.class);
        startActivity(intent);
    }
    public void openStudShop() {
        Intent intent = new Intent(this, StudentShopActivity.class);
        startActivity(intent);
    }

}

