package com.example.woops.cookit.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.woops.cookit.R;

public class VideoAty extends AppCompatActivity {

    private VideoView vv_video;
    private MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_aty);
        vv_video = (VideoView) findViewById(R.id.vv_video);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.video;
        vv_video.setVideoURI(Uri.parse(uri));
        vv_video.start();


    }
}
