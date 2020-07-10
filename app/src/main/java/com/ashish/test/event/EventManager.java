package com.ashish.test.event;

import android.content.Context;


import com.ashish.test.model.PixabayResponse;

import org.greenrobot.eventbus.EventBus;



public class EventManager {
    private Context context;
    private final EventBus eventBus;

    private static class Singleton {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Singleton.INSTANCE;
    }

    private EventManager() {
        eventBus = EventBus.getDefault();
    }

    public void start(Context context) {
        this.context = context;
        eventBus.register(context);
    }

    public void stop() {
        eventBus.unregister(context);
    }

    public void postImageSearchResult(PixabayResponse pixabayResponse) {
        eventBus.post(pixabayResponse);
    }

    public void postErrorEvent(ErrorEvent errorEvent) {
        eventBus.post(errorEvent);
    }

    public void postImageDetailsEvent(ImageDetailsEvent imageDetailsEvent) {
        eventBus.postSticky(imageDetailsEvent);
    }

    public void clearImageDetailsEvent() {
        eventBus.removeStickyEvent(ImageDetailsEvent.class);
    }
}
