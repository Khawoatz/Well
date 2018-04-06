package com.example.khawoat_rmbp.well.Reservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Reservation_location extends AppCompatActivity {

    private TextView get_place, tv_Area;
    private ImageView img_Map;
    private Button Btn_Next;
    String[] Dialog_district;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_location);

        Dialog_district = getResources().getStringArray(R.array.district_array);
        get_place = (TextView) findViewById(R.id.location_place);
        Btn_Next = (Button) findViewById(R.id.btn_Next_Location);
        tv_Area = (TextView) findViewById(R.id.location_area);

        tv_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Reservation_location.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(Dialog_district, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Area = Dialog_district[i].toString();
                        tv_Area.setText(Dialog_district[i]);
                        dialogInterface.dismiss();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Area",Area).commit();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });



        Btn_Next.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){

    }
    });

        get_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(Reservation_location.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data,this);
                Geocoder geocoder = new Geocoder(this);
                try
                {
                    List<Address> addresses = geocoder.getFromLocation(place.getLatLng().latitude,place.getLatLng().longitude, 1);
                    Address obj = addresses.get(0);
                    String add = obj.getAddressLine(0);
                    add = add + "\n" + obj.getCountryName();
                    add = add + "\n" + obj.getCountryCode();
                    add = add + "\n" + obj.getAdminArea();
                    add = add + "\n" + obj.getPostalCode();
                    add = add + "\n" + obj.getSubAdminArea();
                    add = add + "\n" + obj.getLocality();
                    add = add + "\n" + obj.getSubThoroughfare();
                    Log.v("IGA", "Address" + add);

                } catch (IOException e)
                {

                    e.printStackTrace();
                }
                String address = String.format("Place: %s",place.getAddress());
                Double Latitude = place.getLatLng().latitude;
                Double Longtitude = place.getLatLng().longitude;
                String LatString = Latitude.toString();
                String LongString = Longtitude.toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Address",address).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Lat",LatString).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Long",LongString).commit();
                get_place.setText(address);
            }
        }
    }


}
