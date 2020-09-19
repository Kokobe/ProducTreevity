package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherHomeActivity extends AppCompatActivity {
    private Button button5; //timer button
    private Button button7; //teacher stats button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimerBtn();
            }
        });
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStatsBtn();
            }
        });
    }
    public void openTimerBtn() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }
    public void openStatsBtn() {
        Intent intent = new Intent(this, TeacherStatsActivity.class);
        startActivity(intent);
    }
}
