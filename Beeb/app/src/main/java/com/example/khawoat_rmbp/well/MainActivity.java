package com.example.khawoat_rmbp.well;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String URL_FROM_INSERT_TOKEN = "http://203.158.131.67/~Adminwell/App/Insert_Token_User.php";
    private static final String URL_FROM_CHECK_TOKEN = " http://203.158.131.67/~Adminwell/App/check-token_user.php";


    private String token;
    private String IDCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

        IDCus = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Cus_id", "Null Value");

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        token = FirebaseInstanceId.getInstance().getToken();


        Log.d("tokenja", token);
        checktoken(IDCus);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        Button signin = (Button) findViewById(R.id.BtnLogin);
        Button signup = (Button) findViewById(R.id.btn_sign_up);
        TextView tvsigninmas = (TextView) findViewById(R.id.tv_signin_masseuse);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RSUlight.ttf");
        signup.setTypeface(custom_font);
        signin.setTypeface(custom_font);
        tvsigninmas.setTypeface(custom_font);
        tvsigninmas.setPaintFlags(tvsigninmas.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginUser.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        tvsigninmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginMasseuse.class));
            }
        });

    }

    private void checktoken(final String IDCus) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_CHECK_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("logreponscheck",response.toString());
                if (response.toString().equals("false")){
                    InsertToken(IDCus,token);

                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            //ส่งค่าไป php
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cus_id", IDCus);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                Log.d("select Map: ", String.valueOf(IDCus));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

    private void InsertToken(final String IDCus,final String token) {

        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_INSERT_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("logreponsinsert",response.toString());

                if (response.toString().equals("false")){

                }else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            //ส่งค่าไป php
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID_CUS", IDCus);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("Token", token);
                Log.d("select Map: ", String.valueOf(IDCus));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
