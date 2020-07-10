package com.ashish.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ashish.test.event.EventManager;


public class BaseActivity extends AppCompatActivity {

    protected EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventManager = EventManager.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventManager.start(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventManager.stop();
    }

}
