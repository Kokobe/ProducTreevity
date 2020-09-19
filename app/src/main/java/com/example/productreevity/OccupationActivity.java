
package com.example.productreevity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OccupationActivity extends AppCompatActivity {
    private ImageView button2; //student button
    private ImageView button3; //teacher button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupation);
        button2 = (ImageView) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudent();
            }
        });
        button3 = (ImageView) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTeacher();
            }
        });
    }
    public void openStudent() {
        Intent intent = new Intent(this, StudentSignUpActivity.class);
        startActivity(intent);
    }
    public void openTeacher() {
        Intent intent = new Intent(this, TeacherLogInActivity.class);
        startActivity(intent);
    }
}
