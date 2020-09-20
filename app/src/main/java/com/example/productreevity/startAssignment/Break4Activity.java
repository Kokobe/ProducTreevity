package com.example.productreevity.startAssignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//Power Nap
import com.example.productreevity.R;

public class Break4Activity extends AppCompatActivity {
    private Button button22; //timer button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break4);
        button22 = (Button) findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreak4toTimer();
            }
        });
    }
    public void openBreak4toTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }
}