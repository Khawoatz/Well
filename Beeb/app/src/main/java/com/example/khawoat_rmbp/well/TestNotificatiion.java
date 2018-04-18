package com.example.khawoat_rmbp.well;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by jah on 4/16/2018.
 */

public class TestNotificatiion extends AppCompatActivity {

    private String token;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_test);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d("tokenja", token);


    }

}
