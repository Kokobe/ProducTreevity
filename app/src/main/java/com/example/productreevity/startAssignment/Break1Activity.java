package com.example.productreevity.startAssignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
//Drink a Glass of Water


import com.example.productreevity.R;

public class Break1Activity extends AppCompatActivity {
    private Button button4; //timer button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break1);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreak1toTimer();
            }
        });

        VideoView videoView = (VideoView) findViewById(R.id.videoView2);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.relaxvid));
        videoView.start();
    }
    public void openBreak1toTimer() {
        Intent intent = new Intent(this, TimerMainActivity.class);
        startActivity(intent);
    }


}