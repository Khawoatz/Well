package com.example.khawoat_rmbp.well;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Fragment_Masseuse.MainFragmentMasseuse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mass_Choose_Work extends AppCompatActivity {

    private static final String URL_FROM_CHOOSE_WORK = "http://203.158.131.67/~Adminwell/App/Insert_Choose_Work.php";

    AlertDialog mDialog = null;
    /**
     * This becomes false when "Select All" is selected while deselecting some other item on list.
     */
    boolean selectAll = true;
    /**
     * Number of items in array list and eventually in ListView
     */
    int length;
    CheckBox herbal,acu,oil,foot,mig,sport;
    TextView tv_district_mass;
    Dialog dialog;
    Button btn_next;
    String str [] = new String[50];
    private String Mass_ID;
    private String herbalchoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass__choose__work);

        herbal = (CheckBox) findViewById(R.id.cb_Herbal);
        acu = (CheckBox) findViewById(R.id.cb_Acu);
        oil = (CheckBox) findViewById(R.id.cb_Oil);
        foot = (CheckBox) findViewById(R.id.cb_Foot);
        mig = (CheckBox) findViewById(R.id.cb_Mig);
        sport = (CheckBox) findViewById(R.id.cb_Sport);

        btn_next = (Button) findViewById(R.id.btn_next_Main);


        Mass_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("IDMass", "Null");


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("Logchoosework",Mass_ID);
               // Log.d("Logchoosechoosework",herbalchoose);
              //  insertareatype(Mass_ID, herbalchoose);
                if (herbal.isChecked()){
                    herbalchoose = "1";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("herbaltype",herbalchoose).commit();
                    Log.d("Logchoosework",Mass_ID);
                     Log.d("Logchoosechoosework",herbalchoose);
                      insertareatype(Mass_ID, herbalchoose);
                }
                if (acu.isChecked()){
                    String acuchoose = "2";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("acutype",acuchoose).commit();
                    insertareatype(Mass_ID, acuchoose);
                }
                if (oil.isChecked()){
                    String oilchoose = "3";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("oiltype",oilchoose).commit();
                    insertareatype(Mass_ID, oilchoose);
                }
                if (foot.isChecked()){
                    String footchoose = "4";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("foottype",footchoose).commit();
                    insertareatype(Mass_ID, footchoose);
                }
                if (mig.isChecked()){
                    String migchoose = "5";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("migtype",migchoose).commit();
                    insertareatype(Mass_ID, migchoose);
                }
                if (sport.isChecked()){
                    String sportchoose = "6";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("sporttype",sportchoose).commit();
                    insertareatype(Mass_ID, sportchoose);
                }
                Intent i = new Intent(Mass_Choose_Work.this, Mass_Choos_Work__Area.class);
                startActivity(i);
            }
        });
//        length = getResources().getStringArray(R.array.district_array_mass).length;
    }

    private void insertareatype(final String mass_id,final String herbalchoose) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_CHOOSE_WORK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Logresponsework",response.toString());
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
                params.put("Type_id", herbalchoose);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("Masseuse_id", mass_id);


                //  Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

}
