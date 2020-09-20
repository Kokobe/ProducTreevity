package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Drink a Glass of Water
public class Break1Activity extends AppCompatActivity {
    private Button button4; //timer button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break1);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreak1toTimer();
            }
        });
    }
    public void openBreak1toTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }

}