package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.LoginMasseuse;
import com.example.khawoat_rmbp.well.RecyclerView.DataRecyclerView.DataNoti;
import com.example.khawoat_rmbp.well.RecyclerView.MassNotiRecyclerView;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.SignUpMasseuse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationMasseuse extends Fragment {

    private static final String URL_FROM_NOTI = "http://203.158.131.67/~Adminwell/App/ReviewNotiMass.php";
    private static final String URL_FROM_AVG = "http://203.158.131.67/~Adminwell/App/ReviewAvgMass.php";
    List<DataNoti> dataNotiList;
    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    private String IDReview;

    private JSONArray result;
    private ArrayList<String> arrayList;
    private String IDMass;
    private ImageView start,start0,start1,start2,start3,start4,start5,start1h5,start2h5,start3h5,start4h5;
    private TextView tvNameCuss,tvDescription;


    public NotificationMasseuse() {
        // Required empty public constructor
    }


    public static NotificationMasseuse newInstance(String content) {
        NotificationMasseuse fragment = new NotificationMasseuse();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      // final View[] view = {inflater.inflate(R.layout.fragment_notification_masseuse,null)};

      final View view = inflater.inflate(R.layout.fragment_notification_masseuse, container, false);

        IDMass = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("IDMass" , "Null Value");//การรับค่า
        //Toast.makeText(getContext(),IDMass,Toast.LENGTH_SHORT).show();

        Log.d("IDMass",IDMass);
        getdatalist(IDMass);
        selectDetailGatage(IDMass);


        start = (ImageView) view.findViewById(R.id.start);
        start0 = (ImageView) view.findViewById(R.id.start0);
        start1 = (ImageView) view.findViewById(R.id.start1);
        start2 = (ImageView) view.findViewById(R.id.start2);
        start3 = (ImageView) view.findViewById(R.id.start3);
        start4 = (ImageView) view.findViewById(R.id.start4);
        start5 = (ImageView) view.findViewById(R.id.start5);

        start1h5 = (ImageView) view.findViewById(R.id.start1h5);
        start2h5 = (ImageView) view.findViewById(R.id.start2h5);
        start3h5 = (ImageView) view.findViewById(R.id.start3h5);
        start4h5 = (ImageView) view.findViewById(R.id.start4h5);




        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
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
        // Inflate the layout for this fragment
       // TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
//        mTitleBar.setTitle("ข้อความข่าวสาร");
//        mTitleBar.setLeftButtonImage(R.drawable.notificationicontitle,15,15);
//        return view;

        return view;

    }

    private void selectDetailGatage(final String id) {
        // Tag used to cancel the request
        String cancel_req_tag = "select";

        StringRequest strReq = new StringRequest(Request.Method.POST,URL_FROM_AVG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject j0bj = new JSONObject(response);
                    String Avgpoints = j0bj.getString("Avgpoints");
                    if(Avgpoints.equals("null")){
                        start0.setVisibility(View.VISIBLE);

                    }else {
                        double avg = Double.parseDouble(Avgpoints);
                        Log.d("lol", String.valueOf(avg));

                        start1.setVisibility(View.VISIBLE);
                        if (avg == 0) {

                            start0.setVisibility(View.VISIBLE);
                        } else if (avg >= 0.1 && avg <= 0.5) {

                            start.setVisibility(View.VISIBLE);

                        } else if (avg >= 0.6 && avg <= 1) {

                            start1.setVisibility(View.VISIBLE);

                        } else if (avg >= 1.1 && avg <= 1.5) {

                            start1h5.setVisibility(View.VISIBLE);

                        } else if (avg >= 1.6 && avg <= 2) {

                            start2.setVisibility(View.VISIBLE);

                        } else if (avg >= 2.1 && avg <= 2.5) {

                            start2h5.setVisibility(View.VISIBLE);

                        } else if (avg >= 2.6 && avg <= 3) {

                            start3.setVisibility(View.VISIBLE);

                        } else if (avg >= 3.1 && avg <= 3.5) {

                            start3h5.setVisibility(View.VISIBLE);

                        } else if (avg >= 3.6 && avg <= 4) {

                            start4.setVisibility(View.VISIBLE);

                        } else if (avg >= 4.1 && avg <= 4.5) {

                            start4h5.setVisibility(View.VISIBLE);

                        } else if (avg >= 4.6 && avg <= 5) {

                            start5.setVisibility(View.VISIBLE);

                        }
                    }




                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getContext(),"เฮ้อออออออออออออออออออออออออออออออออ",Toast.LENGTH_SHORT).show();

                }
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
                params.put("MS_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                //Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);

    }

    private void getdatalist(final String id) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_NOTI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataNotiList = Arrays.asList(mGson.fromJson(response, DataNoti[].class));
                MassNotiRecyclerView adapter = new MassNotiRecyclerView(getContext(), dataNotiList);
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
        AppSingleton.getInstance(getContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

}
