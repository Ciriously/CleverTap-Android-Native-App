package com.example.clever_app;

import android.app.Application;
import android.app.NotificationManager;
import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;

public class myApplication extends Application {
    @Override
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        CleverTapAPI.setNotificationHandler(new PushTemplateNotificationHandler());
        // NOTIFICATION CHANNEL
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"channel_1","Channel 1","Testing notification channel", NotificationManager.IMPORTANCE_MAX,true);
    }
}