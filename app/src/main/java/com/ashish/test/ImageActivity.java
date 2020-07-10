package com.ashish.test;




import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;


import com.ashish.test.event.EventManager;
import com.ashish.test.event.ImageDetailsEvent;
import com.ashish.test.model.Image;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;


public class ImageActivity extends BaseActivity {

    public ImageView imageView;

    private EventManager eventManager;
    private Image image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageView=findViewById(R.id.imageView);
        eventManager = EventManager.getInstance();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageDetailsEvent(ImageDetailsEvent imageDetailsEvent) {
        eventManager.clearImageDetailsEvent();
        this.image = imageDetailsEvent.getImage();
        Glide.with(this).load(image.getWebformatURL()).into(imageView);
    }




}