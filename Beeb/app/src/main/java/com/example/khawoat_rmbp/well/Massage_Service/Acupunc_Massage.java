package com.example.khawoat_rmbp.well.Massage_Service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.Reservation.reservation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Acupunc_Massage extends AppCompatActivity {
    private static final String URL_FOR_SHOW_MT = " http://203.158.131.67/~Adminwell/App/Show_Massagetype_user.php";

    private Button btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acupunc__massage);

        btnReserve = (Button) findViewById(R.id.btn_reserve);

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mstype = "2";
                gePrice(mstype);
                Intent intent = new Intent(Acupunc_Massage.this,reservation.class);
                startActivity(intent);
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("MassageType",mstype).commit();

            }
        });

        changeStatusBarColor();
    }

    private void gePrice(final String id) {

        String cancel_req_tag = "select";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_SHOW_MT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.d("Log response acup",response.toString());
                    String Price = jObj.getString("Price");
                    String NameTpye = jObj.getString("TypeName");

                    Log.d("Log price acp",Price.toString());
                    Log.d("Log name acp",NameTpye.toString());

                   PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("MassageTypePrice",Price).commit();
                   PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("NameMassageType",NameTpye).commit();




                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error e saaaaaaaaaaaaaaaaa", Toast.LENGTH_LONG).show();
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
                params.put("MTY_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                //Log.d("select Map: ", String.valueOf(id));
                return params;
            }

        };
        // Adding request to request queue
        AppSingleton.getInstance(this).addToRequestQueue(strReq, cancel_req_tag);
    }


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFCC00"));
        }
    }
}
