package com.ashish.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashish.test.adapter.ImageRecyclerViewAdapter;
import com.ashish.test.event.ErrorEvent;
import com.ashish.test.event.EventManager;
import com.ashish.test.event.ImageDetailsEvent;
import com.ashish.test.model.PixabayResponse;
import com.ashish.test.service.PixabayServiceProvider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {
    private PixabayServiceProvider pixabayServiceProvider;
    private EventManager eventManager;
    private ImageRecyclerViewAdapter recyclerViewAdapter;


    RecyclerView imageRecyclerView;


    ProgressBar progressBar;


    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageRecyclerView=findViewById(R.id.recycler_view);
        progressBar=findViewById(R.id.progress_bar);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        eventManager = EventManager.getInstance();
        pixabayServiceProvider = PixabayServiceProvider.getInstance();
        pixabayServiceProvider.editorsChoice();

        recyclerViewAdapter = new ImageRecyclerViewAdapter(this, eventManager);
        imageRecyclerView.setAdapter(recyclerViewAdapter);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pixabayServiceProvider.editorsChoice();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPixabayResponse(PixabayResponse response) {
        if (response.getImages().size() > 0) {
            recyclerViewAdapter.clear();
            recyclerViewAdapter.addAll(response.getImages());
            swipeRefreshLayout.setRefreshing(false);
        } else {
            Toast.makeText(this, "No images available", Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        Toast.makeText(this, errorEvent.getMessage(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImageDetailsEvent(ImageDetailsEvent imageDetailsEvent) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }
}