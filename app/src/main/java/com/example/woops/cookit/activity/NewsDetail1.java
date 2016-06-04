package com.example.woops.cookit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.woops.cookit.R;

public class NewsDetail1 extends AppCompatActivity {

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail1);

        mFab = (FloatingActionButton)findViewById(R.id.fab);


    }

    public void startOtherActivity(View view) {
        startActivity(new Intent(this,VideoAty.class));
    }
}
