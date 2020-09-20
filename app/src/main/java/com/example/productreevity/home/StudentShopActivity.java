package com.example.productreevity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.productreevity.R;

public class StudentShopActivity extends AppCompatActivity {
    private ImageView button12; //Donation One
    private ImageView button19; //Donation Two
    private ImageView button20; //DonationThree
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_shop);
        button12 = (ImageView) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDonationOne();
            }
        });
        button19 = (ImageView) findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDonationTwo();
            }
        });
        button20 = (ImageView) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDonationThree();
            }
        });
    }
    public void openDonationOne() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
    public void openDonationTwo() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
    public void openDonationThree() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
}