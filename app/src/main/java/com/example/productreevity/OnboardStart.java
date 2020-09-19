package com.example.productreevity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class OnboardStart extends AppCompatActivity {
    private ImageView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ImageView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openOnboard1();
                Log.e("OnboardStart", "click");
            }
        });
    }
    public void openOnboard1() {
        Intent intent = new Intent(this, Onboard1.class);
        startActivity(intent);
    }
}