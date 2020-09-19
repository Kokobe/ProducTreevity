package com.example.productreevity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

public class TimerMain extends AppCompatActivity {

    public void runTimer(View view) {
        Countdown countdown = new Countdown(0, 1, 7);
        final Button startButton = (Button) findViewById(R.id.start_timer);
        startButton.setVisibility(View.INVISIBLE);
        final TextView mainTimer = (TextView) findViewById(R.id.main_timer);
        mainTimer.setVisibility(View.VISIBLE);
        mainTimer.setText(countdown.toString());
        new CountDownTimer((long)(countdown.getSecs()*1000), 1000) {
            public void onTick(long millisUntilFinished) {
                Countdown countdown = new Countdown(millisUntilFinished);
                mainTimer.setText(countdown.toString());
            }
            public void onFinish() {
                mainTimer.setText("FINIS");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_main_activity);
//        runTimer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}