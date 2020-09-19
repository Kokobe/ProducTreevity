package com.example.productreevity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MyNotificationService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    public static final String ACTION_PAUSE = "ACTION_PAUSE";

    public static final String ACTION_PLAY = "ACTION_PLAY";

    NotificationChannel chan;
    NotificationManager manager = null;
    Service ref;

    public MyNotificationService() {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_FOREGROUND_SERVICE, "My foreground service onCreate().");
        ref = this;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        stoptimertask();
        super.onDestroy();

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if(action!=null)

                switch (action) {
                    case ACTION_START_FOREGROUND_SERVICE:
                        startForegroundService();
                        Toast.makeText(getApplicationContext(), "Foreground service is started.", Toast.LENGTH_LONG).show();
                        break;
                    case ACTION_STOP_FOREGROUND_SERVICE:
                        stopForegroundService();
                        Toast.makeText(getApplicationContext(), "Foreground service is stopped.", Toast.LENGTH_LONG).show();
                        break;
                    case ACTION_PLAY:
                        Toast.makeText(getApplicationContext(), "You click Play button.", Toast.LENGTH_LONG).show();
                        break;
                    case ACTION_PAUSE:
                        Toast.makeText(getApplicationContext(), "You click Pause button.", Toast.LENGTH_LONG).show();
                        break;
                }
        }
        super.onStartCommand(intent, flags, startId);
        startTimer();

        return START_STICKY;
    }

    /* Used to build and start foreground service. */
    private void startForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Start foreground service.");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("my_service", "My Background Service");
        } else {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName) {
        ///Intent resultIntent = new Intent(this, StartActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntentWithParentStack(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        chan = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);
    }


    private void stopForegroundService() {
        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        // Stop foreground service and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }


    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 5;

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    public void run() {

                        Intent intent = new Intent(ref, TimerMainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        PendingIntent pendingIntent = PendingIntent.getActivity(ref, 0, intent, 0);

                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(ref, chan.getId());
                        Notification notification = notificationBuilder.setOngoing(true)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Please return to Activity")
                                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setContentIntent(pendingIntent) //intent
                                .build();
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ref);
                        notificationManager.notify(1, notificationBuilder.build());
                        startForeground(1, notification);

                    }
                });
            }
        };
    }
}