package com.example.khawoat_rmbp.well.Reservation;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khawoat_rmbp.well.R;

public class Reservation_Result extends AppCompatActivity {

    private String Customer_ID;
    private String Massage_Type;
    private String Date_Reserve;
    private String Date_Start;
    private String Date_End;
    private String Address_Location;
    private String Lat_titude;
    private String Long_titude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__result);

        Customer_ID = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType","Null");

    }
}
