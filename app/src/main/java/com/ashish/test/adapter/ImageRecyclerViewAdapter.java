package com.ashish.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import com.ashish.test.R;
import com.ashish.test.event.EventManager;
import com.ashish.test.event.ImageDetailsEvent;
import com.ashish.test.model.Image;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;





public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.CustomViewHolder> {
    private final EventManager eventManager;
    private List<Image> imageList;
    private Context context;

    public ImageRecyclerViewAdapter(Context context, EventManager eventManager) {
        this.context = context;
        this.imageList = new ArrayList<>();
        this.eventManager = eventManager;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Image image = imageList.get(position);
        if (image.getWebformatURL() != null) {
            Glide.with(context).load(image.getWebformatURL())
                    .error(android.R.drawable.picture_frame)
                    .placeholder(null)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    public void clear() {
        if (imageList.size() > 0) {
            imageList.clear();
            notifyDataSetChanged();
        }
    }

    public void addAll(List<Image> imageList) {
        this.imageList.addAll(imageList);
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Image image = imageList.get(position);
            ImageDetailsEvent imageDetailsEvent = new ImageDetailsEvent(image);
            eventManager.postImageDetailsEvent(imageDetailsEvent);
        }
    }
}
