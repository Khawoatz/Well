package com.example.khawoat_rmbp.well;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.khawoat_rmbp.well.Fragment_Masseuse.NotificationMasseuse;
import com.example.khawoat_rmbp.well.Fragment_User.PendingWork;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by jah on 4/16/2018.
 */

public class FirebaseMassagingService extends com.google.firebase.messaging.FirebaseMessagingService  {
    public  void onMessageReceived(RemoteMessage remoteMessage){
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {
        Intent i = new Intent(this,PendingWork.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM test")
                .setContentText(message)
                .setSmallIcon(R.drawable.logowellpros)
                .setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
