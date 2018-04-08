package com.example.khawoat_rmbp.well.Reservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.example.khawoat_rmbp.well.Massage_Service.Acupunc_Massage;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation_ChooseMass extends AppCompatActivity {

    private TextView tvMassage;
//   private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    List<DataProfile> dataProfileList;
    RecyclerView recyclerView;

    private static final String URL_FROM_SELECT_MASS = "http://203.158.131.67/~Adminwell/App/Reserve_SelectMass_User.php";
    private static final String URL_FROM_COUNT_MASS = "http://203.158.131.67/~Adminwell/App/ReviewNotiMass.php";
    private String MassType,Date_Reserve,Time_Start,Time_End,Area_Dis,Address,Price;
    private View view;
    private TextView tv_Massage,tv_Calendar,tv_Stime,tv_Etime,tv_Location,tv_Price;
    private Button btn_choose,btm_cancel;


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

//       CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
//        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
  //      fragmentCardShadowTransformer.enableScaling(true);

        //viewPager.setAdapter(pagerAdapter);
 //       viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        //viewPager.setOffscreenPageLimit(3);

        MassType = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("NameMassageType","Null");
        Date_Reserve = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateService", "Null Value");
        Time_Start = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateStart", "Null Value");
        Time_End = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("dateEnd", "Null Value");
        Area_Dis = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Area", "Null Value");
        Address = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Address", "Null Value");
        Price = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("priceTime", "Null Value");



        tv_Massage = (TextView)findViewById(R.id.tv_Massage);
        tv_Calendar = (TextView)findViewById(R.id.tv_Calendar);
        tv_Stime = (TextView)findViewById(R.id.tv_Stime);
        tv_Etime = (TextView)findViewById(R.id.tv_Etime);
        tv_Location = (TextView)findViewById(R.id.tv_Location);
        tv_Price = (TextView)findViewById(R.id.tv_Price);


        tv_Massage.setText(MassType);
        tv_Calendar.setText(Date_Reserve);
        tv_Stime.setText(Time_Start);
        tv_Etime.setText(Time_End);
        tv_Location.setText(Address);
        tv_Price.setText(Price);





        Log.d("champppp",Date_Reserve);
        Log.d("jahhhhh",Time_Start);
        Log.d("jibbbbb",Time_End);
        Log.d("june",MassType);
        Log.d("jewwww",Area_Dis);

        Log.d("kuuuuuu",Price);




       // getProfileSelect(Customer_ID);
     //   getProfileCount(Date_Reserve,Time_Start,Time_End,Area_Dis);
        getProfileSelect(Date_Reserve,Time_Start,Time_End,Area_Dis);
        new Dialog(getApplicationContext());

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

                final String getId = dataProfileList.get(RecyclerViewClickedItemPOS).getId();
                final String getnamemass = dataProfileList.get(RecyclerViewClickedItemPOS).getNameMass();
                   dialogchoose(getId,getnamemass);


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

    private void dialogchoose(final String id,final String name) {
        final Dialog dialog = new Dialog(Reservation_ChooseMass.this);
        dialog.setContentView(R.layout.dialog_choosemass);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);



        btn_choose = (Button) dialog.findViewById(R.id.btn_choose);
        btm_cancel = (Button) dialog.findViewById(R.id.btm_cancel);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("logIDmass",id.toString());
                Log.d("logNamemass",name.toString());
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Mas_id", id).commit();

                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Get_nameMass", name).commit();
                Intent i = new Intent(Reservation_ChooseMass.this,Reservation_Result.class);
                startActivity(i);



            }
        });
        btm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




        dialog.show();
    }

    private void selectmass(final String id) {

    }

    private void getProfileSelect(final String Date_Reserve,final String Time_Start,final String Time_End,final String Area_Dis)  {
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
                params.put("Date", Date_Reserve);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("StartTime", Time_Start);
                params.put("EndTime", Time_End);
                params.put("District", Area_Dis);

                //  Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }
    private void getProfileCount(final String id, String time_Start, String time_End, String area_Dis) {
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
                params.put("Date", Date_Reserve);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                params.put("StartTime", Time_Start);
                params.put("EndTime", Time_End);
                params.put("District", Area_Dis);

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
//    public static float dpToPixels(int dp, Context context) {
//        return dp * (context.getResources().getDisplayMetrics().density);
//    }
}
