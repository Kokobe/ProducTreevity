package com.example.productreevity.startAssignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Yoga

import com.example.productreevity.R;

public class Break2Activity extends AppCompatActivity {
    private Button button15; //timer button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break2);
        button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreak2toTimer();
            }
        });

    }
    public void openBreak2toTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }
}