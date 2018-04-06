package com.example.khawoat_rmbp.well.Reservation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.CardFragmentPagerAdapter;
import com.example.khawoat_rmbp.well.Adapter.DataProfile;
import com.example.khawoat_rmbp.well.Adapter.MassProfileRecyclerView;
import com.example.khawoat_rmbp.well.Adapter.ShadowTransformer;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation_ChooseMass extends AppCompatActivity {

    private TextView tvMassage;
//    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    List<DataProfile> dataProfileList;
    RecyclerView recyclerView;

    private static final String URL_FROM_SELECT_MASS = "http://203.158.131.67/~Adminwell/App/ReviewNotiMass.php";
    private static final String URL_FROM_COUNT_MASS = "http://203.158.131.67/~Adminwell/App/ReviewNotiMass.php";
    private String MassType,Date_Reserve,Time_Start,Time_End;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_reservation__choose_mass);
        //ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
      //  LinearLayout llSearchMass = (LinearLayout) findViewById(R.id.ll_searchMass);
      //  llSearchMass.setVisibility(View.INVISIBLE);
       // LinearLayout llChooseMass = (LinearLayout) findViewById(R.id.ll_ChooseMass);
     //   llChooseMass.setVisibility(View.VISIBLE);


     // final View view = getLayoutInflater().inflate(R.layout.activity_reservation__choose_mass, null);
       // final View view = inflater.inflate(R.layout.fragment_notification_masseuse, container, false);

        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
//        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
  //      fragmentCardShadowTransformer.enableScaling(true);

        //viewPager.setAdapter(pagerAdapter);
 //       viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        //viewPager.setOffscreenPageLimit(3);

        MassType = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType","Null");
        Date_Reserve = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateService", "Null Value");
        Time_Start = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateStart", "Null Value");
        Time_End = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateEnd", "Null Value");

        Log.d("champppp",Date_Reserve);
        Log.d("jahhhhh",Time_Start);
        Log.d("jibbbbb",Time_End);
        Log.d("june",MassType);

       // getProfileSelect(Customer_ID);
        //getProfileCount(Customer_ID);

        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);///ใช้ viewpager แต่วิวได้มั้ย
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                View view = Recyclerview.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if (view !=null && gestureDetector.onTouchEvent(motionEvent) ){
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(view);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }
    private void getProfileSelect(final String date,final String starT,final String endT,final String dis) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_SELECT_MASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataProfileList = Arrays.asList(mGson.fromJson(response, DataProfile[].class));
                MassProfileRecyclerView adapter = new MassProfileRecyclerView(getApplicationContext(), dataProfileList);
                recyclerView.setAdapter(adapter);
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
                params.put("Date", date);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("StartTime", starT);
                params.put("EndTime", endT);
                params.put("เขตบางนา", dis);
                //  Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }
    private void getProfileCount(final String id) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_COUNT_MASS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataProfileList = Arrays.asList(mGson.fromJson(response, DataProfile[].class));
                MassProfileRecyclerView adapter = new MassProfileRecyclerView(getApplicationContext(), dataProfileList);
                recyclerView.setAdapter(adapter);
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
                params.put("MS_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

    /**
     * Change value in dp to pixels
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
