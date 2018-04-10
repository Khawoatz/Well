package com.example.khawoat_rmbp.well.Reservation;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;

import java.util.HashMap;
import java.util.Map;

public class Reservation_Result extends AppCompatActivity {

    private static final String URL_FROM_RESER = "http://203.158.131.67/~Adminwell/App/Reserve_user.php";


    private String Customer_ID;
    private String Massage_Type;
    private String Date_Reserve;
    private String Date_End;
    private String Address_Location;
    private String Lat_titude;
    private String Long_titude;
    private Activity view;
    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    private TextView tv_Massage, tv_nameMass, tv_Calendar, tv_Stime, tv_Etime, tv_Location, tv_Price;
    private Button btn_Resver, btn_Cancel;
    private String Mass_ID;
    private String MassageType_ID;
    private String TimeStart;
    private String Price;
    private String TimeEnd;
    private String Name_Mass;
    private String MassageType_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__result);
        //final View view = inflater.inflate(R.layout.activity_reservation__result, container, false);

        tv_Massage = (TextView) findViewById(R.id.tv_Massage);
        tv_nameMass = (TextView) findViewById(R.id.tv_nameMass);
        tv_Calendar = (TextView) findViewById(R.id.tv_Calendar);
        tv_Stime = (TextView) findViewById(R.id.tv_Stime);
        tv_Etime = (TextView) findViewById(R.id.tv_Etime);
        tv_Location = (TextView) findViewById(R.id.tv_Location);
        tv_Price = (TextView) findViewById(R.id.tv_Price);


        btn_Resver = (Button) findViewById(R.id.btn_Resver);
        btn_Cancel = (Button) findViewById(R.id.btn_Cancel);


        Customer_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Cus_id", "Null");

        Mass_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Mas_id", "Null");
        Name_Mass = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Get_nameMass", "Null");

        MassageType_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType", "Null");
        MassageType_Name = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("NameMassageType", "Null");
        Price = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageTypePrice", "Null");

        Date_Reserve = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("dateService", "Null");
        TimeStart = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("dateStart", "Null");
        TimeEnd = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("dateEnd", "Null");

        Address_Location = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Address", "Null");
        Lat_titude = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Lat", "Null");
        Long_titude = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Long", "Null");

        Log.d("id_cus for re", Customer_ID.toString());
        Log.d("id_mass for re", Mass_ID.toString());
        Log.d("id_massT", MassageType_ID.toString());
        Log.d("dte_re", Date_Reserve.toString());
        Log.d("time_re", TimeStart.toString());
        Log.d("time end_re", TimeEnd.toString());
        Log.d("address_re", Address_Location.toString());
        Log.d("price_re", Price.toString());
        Log.d("lat_re", Lat_titude.toString());
        Log.d("lond_re", Long_titude.toString());


        tv_Massage.setText(MassageType_Name);
        tv_nameMass.setText(Name_Mass);
        tv_Calendar.setText(Date_Reserve);
        tv_Stime.setText(TimeStart);
        tv_Etime.setText(TimeEnd);
        tv_Location.setText(Address_Location);
        tv_Price.setText(Price);


        btn_Resver.setOnClickListener(new View.OnClickListener() {
            public String Long_titudeE,Lat_titudeE,Address_LocationT,TimeStartT,TimeEndD,Status_Reser;

            @Override
            public void onClick(View v) {

                Address_LocationT ="เขตสวนหลวง";
                Lat_titudeE = "13.7784954";
                Long_titudeE = "100.5569875";
                TimeStartT = "05:15";
                TimeEndD ="09:15";
                Status_Reser = "Waiting";


                getreser(Customer_ID, Mass_ID, Date_Reserve, TimeStartT, TimeEndD, Address_LocationT,Status_Reser, MassageType_ID, Lat_titudeE, Long_titudeE);
            }
        });


    }

    private void getreser(final String customer_id, final String mass_id, final String date_reserve, final String timeStart,
                          final String timeEnd, final String address_location,final String status_reser ,final String massageType_id,
                          final String lat_titude, final String long_titude) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_RESER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Log response Reser",response.toString());

             //   Toast.makeText(view.getApplicationContext(), "คุณได้ทำการจองเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            //ส่งค่าไป php
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("CT_ID", customer_id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("Mas_ID", mass_id);
                params.put("Date_ID", date_reserve);
                params.put("Start_Time", timeStart);
                params.put("End_Time", timeEnd);
                params.put("Location_ID", address_location);
                params.put("StatusReser_ID", status_reser);
                params.put("MassType_ID", massageType_id);
                params.put("Lat_ID", lat_titude);
                params.put("Long_ID", long_titude);

                //  Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }
}

