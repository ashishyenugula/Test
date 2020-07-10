package com.ashish.test.event;


import com.ashish.test.model.Image;

public class ImageDetailsEvent {

    private final Image image;

    public ImageDetailsEvent(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
