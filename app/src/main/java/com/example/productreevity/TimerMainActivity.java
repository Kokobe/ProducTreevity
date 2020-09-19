package com.example.productreevity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import com.example.productreevity.classes.Countdown;

public class TimerMainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "default";
    final String TAG = "TimerMain";

    public void runTimer(View view) {
        createNotificationChannel();
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
        setContentView(R.layout.activity_timer);
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "pause");
        sendNotification();
    }

    private void createNotificationChannel() {
        Log.e(TAG, "create notification channel");
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void sendNotification() {
        Log.e(TAG, "send notification");
//        Intent intent = new Intent(this, TimerMainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Don't lose your producTreevity")
                .setContentText("Return to the app within 15 seconds and we won't end your session.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 0;
        notificationManager.notify(notificationId, builder.build());
    }
}