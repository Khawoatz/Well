package com.example.khawoat_rmbp.well.Reservation;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.DataNoti;
import com.example.khawoat_rmbp.well.Adapter.DataProfile;
import com.example.khawoat_rmbp.well.Adapter.MassNotiRecyclerView;
import com.example.khawoat_rmbp.well.Adapter.MassProfileRecyclerView;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation_Result extends AppCompatActivity {



    private String Customer_ID;
    private String Massage_Type;
    private String Date_Reserve;
    private String Date_Start;
    private String Date_End;
    private String Address_Location;
    private String Lat_titude;
    private String Long_titude;
    private Activity view;
    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__result);
        //final View view = inflater.inflate(R.layout.activity_reservation__result, container, false);

        Customer_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType", "Null");


    }
}
