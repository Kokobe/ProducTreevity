package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Duolingo
public class Break3Activity extends AppCompatActivity {
    private Button button21; //timer button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break3);
        button21 = (Button) findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreak3toTimer();
            }
        });
    }
    public void openBreak3toTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }
}