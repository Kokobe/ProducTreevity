package com.example.productreevity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import com.example.productreevity.classes.Countdown;

public class TimerMainActivity extends AppCompatActivity {

    final String TAG = "TimerMain";

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
    private Button button16; //to break selection page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
//        runTimer();
        button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreakSel();
            }
        });
    }
    public void openBreakSel() {
        Intent intent = new Intent(this, StudBreakActivity.class);
        startActivity(intent);
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "pause");
        sendNotification();
    }

    private void sendNotification() {

    }
}