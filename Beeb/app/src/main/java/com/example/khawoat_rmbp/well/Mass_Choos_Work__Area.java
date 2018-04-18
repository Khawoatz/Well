package com.example.khawoat_rmbp.well;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Fragment_Masseuse.MainFragmentMasseuse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jah on 4/15/2018.
 */

public class Mass_Choos_Work__Area extends AppCompatActivity {
    private static final String URL_FROM_CHOOSE_WORK_AREA = "http://203.158.131.67/~Adminwell/App/Insert_Choose_Work_Area.php";
    private CheckBox c_01, c_02,c_03,c_04,c_05,c_06,c_07,c_08,c_09,c_10,c_11,c_12,c_13,c_14,c_15,c_16,c_17,c_18,c_19,
                     c_20,c_21,c_22,c_23,c_24,c_25,c_26,c_27,c_28,c_29,c_30,
                     c_31,c_32,c_33,c_34,c_35,c_36,c_37,c_38,c_39,c_40,
                     c_41,c_42,c_43,c_44,c_45,c_46,c_47,c_48,c_49,c_50;
    private Button btn_next;
    private String Mass_ID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mass_choose_work_area);
        c_01 = (CheckBox) findViewById(R.id. cb_01);
        c_02 = (CheckBox) findViewById(R.id. cb_02);
        c_03 = (CheckBox) findViewById(R.id. cb_03);
        c_04 = (CheckBox) findViewById(R.id. cb_04);
        c_05 = (CheckBox) findViewById(R.id. cb_05);
        c_06 = (CheckBox) findViewById(R.id. cb_06);
        c_07 = (CheckBox) findViewById(R.id. cb_07);
        c_08 = (CheckBox) findViewById(R.id. cb_08);
        c_09 = (CheckBox) findViewById(R.id. cb_09);
        c_10 = (CheckBox) findViewById(R.id. cb_10);

        c_11 = (CheckBox) findViewById(R.id. cb_11);
        c_12 = (CheckBox) findViewById(R.id. cb_12);
        c_13 = (CheckBox) findViewById(R.id. cb_13);
        c_14 = (CheckBox) findViewById(R.id. cb_14);
        c_15 = (CheckBox) findViewById(R.id. cb_15);
        c_16 = (CheckBox) findViewById(R.id. cb_16);
        c_17 = (CheckBox) findViewById(R.id. cb_17);
        c_18 = (CheckBox) findViewById(R.id. cb_18);
        c_19 = (CheckBox) findViewById(R.id. cb_19);
        c_20 = (CheckBox) findViewById(R.id. cb_20);

        c_21 = (CheckBox) findViewById(R.id. cb_21);
        c_22 = (CheckBox) findViewById(R.id. cb_22);
        c_23 = (CheckBox) findViewById(R.id. cb_23);
        c_24 = (CheckBox) findViewById(R.id. cb_24);
        c_25 = (CheckBox) findViewById(R.id. cb_25);
        c_26 = (CheckBox) findViewById(R.id. cb_26);
        c_27 = (CheckBox) findViewById(R.id. cb_27);
        c_28 = (CheckBox) findViewById(R.id. cb_28);
        c_29 = (CheckBox) findViewById(R.id. cb_29);
        c_30 = (CheckBox) findViewById(R.id. cb_30);

        c_31 = (CheckBox) findViewById(R.id. cb_31);
        c_32 = (CheckBox) findViewById(R.id. cb_32);
        c_33 = (CheckBox) findViewById(R.id. cb_33);
        c_34 = (CheckBox) findViewById(R.id. cb_34);
        c_35 = (CheckBox) findViewById(R.id. cb_35);
        c_36 = (CheckBox) findViewById(R.id. cb_36);
        c_37 = (CheckBox) findViewById(R.id. cb_37);
        c_38 = (CheckBox) findViewById(R.id. cb_38);
        c_39 = (CheckBox) findViewById(R.id. cb_39);
        c_40 = (CheckBox) findViewById(R.id. cb_40);

        c_41 = (CheckBox) findViewById(R.id. cb_41);
        c_42 = (CheckBox) findViewById(R.id. cb_42);
        c_43 = (CheckBox) findViewById(R.id. cb_43);
        c_44 = (CheckBox) findViewById(R.id. cb_44);
        c_45 = (CheckBox) findViewById(R.id. cb_45);
        c_46 = (CheckBox) findViewById(R.id. cb_46);
        c_47 = (CheckBox) findViewById(R.id. cb_47);
        c_48 = (CheckBox) findViewById(R.id. cb_48);
        c_49 = (CheckBox) findViewById(R.id. cb_49);
        c_50 = (CheckBox) findViewById(R.id. cb_50);

        btn_next = (Button) findViewById(R.id.btn_next_Main);

        Mass_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("IDMass", "Null");

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_01.isChecked()){
                   String k_01 = "1";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_01",k_01).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_01);
                    insertareatype(Mass_ID, k_01);
                }
                if (c_02.isChecked()){
                    String k_02 = "2";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_02",k_02).commit();
                    insertareatype(Mass_ID, k_02);
                }
                if (c_03.isChecked()){
                    String k_03 = "3";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_03",k_03).commit();
                    insertareatype(Mass_ID, k_03);
                }
                if (c_04.isChecked()){
                    String k_04 = "4";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_04",k_04).commit();
                    insertareatype(Mass_ID, k_04);
                }
                if (c_05.isChecked()){
                    String k_05 = "5";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_05",k_05).commit();
                    insertareatype(Mass_ID, k_05);
                }
                if (c_06.isChecked()){
                    String k_06 = "6";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_06",k_06).commit();
                    insertareatype(Mass_ID, k_06);
                }
                if (c_07.isChecked()){
                    String k_07 = "7";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_07",k_07).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_07);
                    insertareatype(Mass_ID, k_07);
                }
                if (c_08.isChecked()){
                    String k_08 = "8";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_08",k_08).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_08);
                    insertareatype(Mass_ID, k_08);
                }
                if (c_09.isChecked()){
                    String k_09 = "9";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_09",k_09).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_09);
                    insertareatype(Mass_ID, k_09);
                }
                if (c_10.isChecked()){
                    String k_10 = "10";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_10",k_10).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_10);
                    insertareatype(Mass_ID, k_10);
                }

                if (c_11.isChecked()){
                    String k_11 = "11";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_11",k_11).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_11);
                    insertareatype(Mass_ID, k_11);
                }
                if (c_12.isChecked()){
                    String k_12 = "12";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_12",k_12).commit();
                    insertareatype(Mass_ID, k_12);
                }
                if (c_13.isChecked()){
                    String k_13 = "13";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_13",k_13).commit();
                    insertareatype(Mass_ID, k_13);
                }
                if (c_14.isChecked()){
                    String k_14 = "14";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_14",k_14).commit();
                    insertareatype(Mass_ID, k_14);
                }
                if (c_15.isChecked()){
                    String k_15 = "15";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_15",k_15).commit();
                    insertareatype(Mass_ID, k_15);
                }
                if (c_16.isChecked()){
                    String k_16 = "16";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_16",k_16).commit();
                    insertareatype(Mass_ID, k_16);
                }
                if (c_17.isChecked()){
                    String k_17 = "17";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_17",k_17).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_17);
                    insertareatype(Mass_ID, k_17);
                }
                if (c_18.isChecked()){
                    String k_18 = "18";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_18",k_18).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_18);
                    insertareatype(Mass_ID, k_18);
                }
                if (c_19.isChecked()){
                    String k_19 = "19";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_19",k_19).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_19);
                    insertareatype(Mass_ID, k_19);
                }
                if (c_20.isChecked()){
                    String k_20 = "20";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_20",k_20).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_20);
                    insertareatype(Mass_ID, k_20);
                }

                if (c_21.isChecked()){
                    String k_21 = "21";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_21",k_21).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_21);
                    insertareatype(Mass_ID, k_21);
                }
                if (c_22.isChecked()){
                    String k_22 = "22";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_22",k_22).commit();
                    insertareatype(Mass_ID, k_22);
                }
                if (c_23.isChecked()){
                    String k_23 = "23";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_23",k_23).commit();
                    insertareatype(Mass_ID, k_23);
                }
                if (c_24.isChecked()){
                    String k_24 = "24";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_24",k_24).commit();
                    insertareatype(Mass_ID, k_24);
                }
                if (c_25.isChecked()){
                    String k_25 = "25";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_25",k_25).commit();
                    insertareatype(Mass_ID, k_25);
                }
                if (c_26.isChecked()){
                    String k_26 = "26";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_26",k_26).commit();
                    insertareatype(Mass_ID, k_26);
                }
                if (c_27.isChecked()){
                    String k_27 = "27";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_27",k_27).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_27);
                    insertareatype(Mass_ID, k_27);
                }
                if (c_28.isChecked()){
                    String k_28 = "28";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_28",k_28).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_28);
                    insertareatype(Mass_ID, k_28);
                }
                if (c_29.isChecked()){
                    String k_29 = "29";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_29",k_29).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_29);
                    insertareatype(Mass_ID, k_29);
                }
                if (c_30.isChecked()){
                    String k_30 = "30";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_30",k_30).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_30);
                    insertareatype(Mass_ID, k_30);
                }

                if (c_31.isChecked()){
                    String k_31 = "31";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_31",k_31).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_31);
                    insertareatype(Mass_ID, k_31);
                }
                if (c_32.isChecked()){
                    String k_32 = "32";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_32",k_32).commit();
                    insertareatype(Mass_ID, k_32);
                }
                if (c_33.isChecked()){
                    String k_33 = "33";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_33",k_33).commit();
                    insertareatype(Mass_ID, k_33);
                }
                if (c_34.isChecked()){
                    String k_34 = "34";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_34",k_34).commit();
                    insertareatype(Mass_ID, k_34);
                }
                if (c_35.isChecked()){
                    String k_35 = "35";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_35",k_35).commit();
                    insertareatype(Mass_ID, k_35);
                }
                if (c_36.isChecked()){
                    String k_36 = "36";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_36",k_36).commit();
                    insertareatype(Mass_ID, k_36);
                }
                if (c_37.isChecked()){
                    String k_37 = "37";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_37",k_37).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_37);
                    insertareatype(Mass_ID, k_37);
                }
                if (c_38.isChecked()){
                    String k_38 = "38";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_38",k_38).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_38);
                    insertareatype(Mass_ID, k_38);
                }
                if (c_39.isChecked()){
                    String k_39 = "39";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_39",k_39).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_39);
                    insertareatype(Mass_ID, k_39);
                }
                if (c_40.isChecked()){
                    String k_40 = "40";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_40",k_40).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_40);
                    insertareatype(Mass_ID, k_40);
                }

                if (c_41.isChecked()){
                    String k_41 = "41";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_41",k_41).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_41);
                    insertareatype(Mass_ID, k_41);
                }
                if (c_42.isChecked()){
                    String k_42 = "42";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_42",k_42).commit();
                    insertareatype(Mass_ID, k_42);
                }
                if (c_43.isChecked()){
                    String k_43 = "43";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_43",k_43).commit();
                    insertareatype(Mass_ID, k_43);
                }
                if (c_44.isChecked()){
                    String k_44 = "44";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_44",k_44).commit();
                    insertareatype(Mass_ID, k_44);
                }
                if (c_45.isChecked()){
                    String k_45 = "45";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_45",k_45).commit();
                    insertareatype(Mass_ID, k_45);
                }
                if (c_46.isChecked()){
                    String k_46 = "46";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_46",k_46).commit();
                    insertareatype(Mass_ID, k_46);
                }
                if (c_47.isChecked()){
                    String k_47 = "47";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_47",k_47).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_47);
                    insertareatype(Mass_ID, k_47);
                }
                if (c_48.isChecked()){
                    String k_48 = "48";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_48",k_48).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_48);
                    insertareatype(Mass_ID, k_48);
                }
                if (c_49.isChecked()){
                    String k_49 = "49";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_49",k_49).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_49);
                    insertareatype(Mass_ID, k_49);
                }
                if (c_50.isChecked()){
                    String k_50 = "50";
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("k_50",k_50).commit();
                    Log.d("Logchoosework",Mass_ID);
                    Log.d("Logchoosechoosework",k_50);
                    insertareatype(Mass_ID, k_50);
                }
                Intent i = new Intent(Mass_Choos_Work__Area.this, MainFragmentMasseuse.class);
                startActivity(i);
            }
        });
    }

    private void insertareatype(final String mass_id,final String k_01) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_CHOOSE_WORK_AREA, new Response.Listener<String>() {
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
                params.put("Service_id", k_01);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("Masseuse_id", mass_id);


                //  Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }
}
