package com.example.productreevity.startAssignment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.TimePicker;

import com.example.productreevity.R;
import com.example.productreevity.classes.Countdown;
import com.example.productreevity.home.StudentHomeActivity;

import org.w3c.dom.Text;

public class TimerMainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "default";
    final String TAG = "TimerMain";
    private boolean running;
    private boolean timerOn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void runTimer(View view) {
        final TimePicker userTime = (TimePicker) findViewById(R.id.user_time);
        final ImageView timeBox = (ImageView) findViewById(R.id.imageView7);
        createNotificationChannel();
        int minutes = userTime.getHour();
        if (minutes == 0)
            minutes += 12;
        int seconds = userTime.getMinute();
        userTime.setVisibility(View.GONE);
        timeBox.setVisibility(View.GONE);
        Countdown countdown = new Countdown(minutes, seconds);
        timerOn = true;
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
                timerOn = false;
                finished();
                recreate();
                finish();
            }
        }.start();
    }

    private Button button16; //to break selection
    private ImageView imageView6; //give-up, back to student home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timerOn =false;
        running = true;
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
//        runTimer();
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGiveUp();
            }
        });
        TextView assignmentTitle = (TextView) findViewById(R.id.assignment_title);
        String caller = getIntent().getStringExtra("CALLER");

        assignmentTitle.setText(caller);
        button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBreakSel();
            }
        });
    }
    public void openGiveUp() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
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
        running = false;
        super.onPause();
        Log.e(TAG, "pause");
        if(timerOn == true)
            leftApp();
    }

    @Override
    protected void onResume() {
        running = true;
        super.onResume();
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
    private void leftApp() {
        Log.e(TAG, "send notification");
        Intent intent = new Intent(this, TimerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Don't lose your producTreevity")
                .setContentText("Return to the app within 15 seconds.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 1;
        notificationManager.notify(notificationId, builder.build());

        CountDownTimer waitTime = new CountDownTimer((long)(18*1000), 1000) {
            public void onTick(long millisUntilFinished) {
                if(running == true) {
                    cancel();
                    notificationManager.cancel(1);
                }
            }
            public void onFinish() {
                notificationManager.cancel(1);
                goneForGood();
            }
        }.start();
    }

    private void goneForGood() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(":(")
                .setContentText("You didn't earn any seeds.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 2;
        notificationManager.notify(notificationId, builder.build());
        Intent newintent = new Intent(this, TimerMainActivity.class);
        startActivity(newintent);
        finish();
    }

    private void finished() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("YAY!!!")
                .setContentText("You earned your seeds.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        int notificationId = 3;
        notificationManager.notify(notificationId, builder.build());
        Intent newintent = new Intent(this, TimerMainActivity.class);
        startActivity(newintent);
        finish();
    }
}