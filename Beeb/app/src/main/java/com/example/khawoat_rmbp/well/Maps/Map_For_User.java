package com.example.khawoat_rmbp.well.Maps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.Modules.DirectionFinder;
import com.example.khawoat_rmbp.well.Modules.DirectionFinderListener;
import com.example.khawoat_rmbp.well.Modules.Route;
import com.example.khawoat_rmbp.well.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Map_For_User extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    String LatGPS,LatGPS_User;
    String LngGPS,LngGPS_User;
    String ResIdMap,ResId_Map;
    double latUser,lngUser,latMass,lngMass;
    private static final String URL_FOR_GET_LOCATION = "http://203.158.131.67/~Adminwell/App/Map_Get_Location.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__for__user);
        ResId_Map = getIntent().getExtras().getString("ResIdMapJa");
        ResIdMap = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Res_Id_Map", "Null");
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarTrack);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_circular_symbol);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);


        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(5000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getLocation(ResId_Map);
                            }
                        });
                    }
                }catch (InterruptedException e){

                }
            }
        };
        t.start();

//        btnFindPath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getLocation("1");
//            }
//        });
    }

    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void sendRequest() {
        LatGPS = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Lat_Mass_GPS", "Null");
        LngGPS = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Long_Mass_GPS", "Null");
        LatGPS_User = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Lat_User_GPS", "Null");
        LngGPS_User = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Long_User_GPS", "Null");

        latUser = Double.parseDouble(LatGPS_User);
        lngUser = Double.parseDouble(LngGPS_User);
        latMass = Double.parseDouble(LatGPS);
        lngMass = Double.parseDouble(LngGPS);
        LatLng LatLngUser = new LatLng(latUser,lngUser);
        LatLng LatLngMass = new LatLng(latMass,lngMass);
//        String origin = etOrigin.getText().toString();
//        String destination = etDestination.getText().toString();
        etOrigin.setText(latMass+","+lngMass);
        etDestination.setText(latUser+","+lngUser);
        String origin =etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        Log.d("oriori",origin);
        Log.d("desti",destination);
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng hcmus = new LatLng(13.778264, 100.556654);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Current Masseuse")
                .position(hcmus)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "กรุณารอสักครู่",
//                "กำลังอัพเดทการติดตาม ..", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            int height = 100;
            int width = 100;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.masseusebase);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.masseusebase);
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions()
                    .geodesic(true)
                    .color(Color.RED)
                    .width(10);

            Toast.makeText(this,"อัพเดทแล้ว",Toast.LENGTH_SHORT);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    private void getLocation(final String id) {
        final String cancel_req_tag = "getlocation";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_FOR_GET_LOCATION, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("LatResponse:", response);
                try {
                    JSONObject jobj = new JSONObject(response);

                    /*** Location Masseuse **/
                    String LatMass = jobj.getString("Lat_Mass");
                    String LongMass = jobj.getString("Long_Mass");

                    /*** Location User **/
                    String LatUser = jobj.getString("Lat");
                    String LongUser = jobj.getString("Longtitude");
//
//                    JSONArray jsonArray = jobj.getJSONArray("Lat_Mass");
//                    JSONArray jsonArray2 = jobj.getJSONArray("Long_Mass");
//                    for (int i = 0; i<jsonArray.length(); i++){
//                        JSONObject respons = jsonArray.getJSONObject(i);
//
//                        m_lat = respons.getDouble("Lat_Mass");
//
//
//                    }
//
//                    for (int i = 0; i<jsonArray2.length(); i++){
//                        JSONObject responss = jsonArray2.getJSONObject(i);
//                        m_long = responss.getDouble("Long_Mass");
//                    }
//
//                    mMass.setPosition(new LatLng(m_lat,m_long));
//                    Toast.makeText(Map_User.this,"เข้ามั้ย",Toast.LENGTH_SHORT);
//
//                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                    builder.include(mMass.getPosition());
//                    LatLngBounds bounds = builder.build();
//                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,0);
//                    mMap.moveCamera(cu);
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Lat_User_GPS",LatUser).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Long_User_GPS",LongUser).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Lat_Mass_GPS",LatMass).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Long_Mass_GPS",LongMass).commit();

                    sendRequest();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            //ส่งค่าไป php
            protected java.util.Map<String, String> getParams() {
                // Posting params to login url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("Res_id", id); //  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

}
