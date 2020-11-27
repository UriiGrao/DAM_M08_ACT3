package com.example.dam_m08_act3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ListIterator;

public class VideoDirectory extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_directory);

        ActivityCompat.requestPermissions(VideoDirectory.this, PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE);

        ListView list=(ListView) findViewById( R.id.listVideos);
        ArrayAdapter<String> nameVideo;
        ArrayList<String> arrayList = new ArrayList<>();
        // Captar ficheros de un directorio en concreto
        //getClass().getResource("raw/noragami.mp4").toURI();
        Field[] fields = R.raw.class.getFields();
        for (Field field:
             fields) {
            arrayList.add(field.getName());
        }
        list.setAdapter(new ArrayAdapter<String>(VideoDirectory.this,
                android.R.layout.simple_list_item_1, arrayList));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = list.getAdapter().getItem(position).toString();
                Intent nextPage = new Intent(VideoDirectory.this, MainActivity.class);
                nextPage.putExtra("videoName", selected);
                startActivity(nextPage);
            }
        });
    }
}