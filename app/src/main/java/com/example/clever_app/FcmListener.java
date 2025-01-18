package com.example.clever_app;
import com.clevertap.android.sdk.pushnotification.fcm.CTFcmMessageHandler;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmListener extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message){
        CTFcmMessageHandler handler = new CTFcmMessageHandler();
        handler.createNotification(getApplicationContext(), message);
    }
}
