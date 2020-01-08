package de.landshut.pluto20_gkw;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG ="xx MyFbMess.Serv.";

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "FROM :" + remoteMessage.getFrom() );

        if (remoteMessage.getNotification() != null){
            Log.d(TAG,"Received Firebase Notification :"
              + "\n Body :" + remoteMessage.getNotification().getBody()
              + "\n Title:" + remoteMessage.getNotification().getTitle());
        }

    }
}
