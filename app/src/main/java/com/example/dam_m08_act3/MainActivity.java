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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    MediaPlayer mp;
    int position = 0;

    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE);

        videoView = (VideoView) findViewById(R.id.videoViewScreen);

        String nameVideo = getIntent().getStringExtra("videoName");

        if(nameVideo == null) {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.noragami));
        } else {
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + nameVideo));
        }
        videoView.start();

        Button buttonPause = (Button) findViewById(R.id.buttonpause);
        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        Button buttonStop = (Button) findViewById(R.id.buttonStop);
        Button buttonSelect = (Button) findViewById(R.id.buttonSelectVideo);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, VideoDirectory.class);
                startActivity(nextPage);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0);
                videoView.pause();
            }
        });
    }


    public void stop(View v) {
        if (mp != null) {
            mp.stop();
            position = 0;
        }
    }
}