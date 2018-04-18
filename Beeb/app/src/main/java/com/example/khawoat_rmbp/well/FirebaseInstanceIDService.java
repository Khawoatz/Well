package com.example.khawoat_rmbp.well;

import android.util.Log;

import com.android.volley.Request;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by jah on 4/16/2018.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("logtoken",token);

    }


}
