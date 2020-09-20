package com.example.productreevity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.productreevity.R;
import com.example.productreevity.startAssignment.TimerMainActivity;

public class TeacherHomeActivity extends AppCompatActivity {

    private ImageView teacher_stats_btn; //teacher stats button
    private ImageView button17; //teacher encouraging words button
    private ImageView button18; //teacher send assignments button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_teacher_home);

        teacher_stats_btn = (ImageView) findViewById(R.id.teacher_stats_btn);
        teacher_stats_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStatsBtn();
            }
        });
        button17 = (ImageView) findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openEncouragingWords();
            }
        });
        button18 = (ImageView) findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSendAssignments();
            }
        });
    }

    public void openStatsBtn() {
        Intent intent = new Intent(this, TeacherStatsActivity.class);
        startActivity(intent);
    }
    public void openEncouragingWords() {
        Intent intent = new Intent(this, TeacherEncouragingWordsActivity.class);
        startActivity(intent);
    }
    public void openSendAssignments() {
        Intent intent = new Intent(this, TeacherSendingAssignmentsActivity.class);
        startActivity(intent);
    }
}