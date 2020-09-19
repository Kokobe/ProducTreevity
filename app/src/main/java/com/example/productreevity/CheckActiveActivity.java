package com.example.productreevity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActiveActivity extends AppCompatActivity {

    final String TAG = "CheckActiveActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.check_active_activity);
        Log.e(TAG, "create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "pause");
        sendNotification();
    }

    private void sendNotification() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "destroy");
    }
}
