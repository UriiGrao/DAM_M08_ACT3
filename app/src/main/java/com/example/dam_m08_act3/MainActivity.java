package com.example.dam_m08_act3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button buttonPause;
    Button buttonPlay;
    Button buttonStop;
    Button buttonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoViewScreen);

        String nameVideo = getIntent().getStringExtra("videoName");

        if (nameVideo == null) {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.noragami));
        } else {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + nameVideo));
        }
        videoView.start();

        buttonPause = findViewById(R.id.buttonpause);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonStop = findViewById(R.id.buttonStop);
        buttonSelect = findViewById(R.id.buttonSelectVideo);

        buttonSelect.setOnClickListener(v -> {
            Intent nextPage = new Intent(MainActivity.this, VideoDirectory.class);
            startActivity(nextPage);
        });

        buttonPause.setOnClickListener(v -> videoView.pause());

        buttonPlay.setOnClickListener(v -> videoView.start());

        buttonStop.setOnClickListener(v -> {
            videoView.seekTo(0);
            videoView.pause();
        });
    }

}