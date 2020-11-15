package com.example.dam_m08_act3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pause(View v) {
        if (mp != null && mp.isPlaying()) {
            position = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void play(View v) {
        if (mp != null && !mp.isPlaying()) {
            mp.seekTo(position);
            mp.start();
        } else {
            mp.release();
            // mp = MediaPlayer.create(this, R.raw.numeros); todo: falta revisar.
        }
    }

    public void stop(View v) {
        if (mp != null) {
            mp.stop();
            position = 0;
        }
    }
}