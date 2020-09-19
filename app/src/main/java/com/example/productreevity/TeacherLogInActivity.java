package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherLogInActivity extends AppCompatActivity {
    private Button button15; //teacher log-in button to teacher home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);
        button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTeachLogInTeachHome();
            }
        });
    }
    public void openTeachLogInTeachHome() {
        Intent intent = new Intent(this, TeacherHomeActivity.class);
        startActivity(intent);
    }
}