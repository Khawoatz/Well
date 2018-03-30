package com.example.khawoat_rmbp.well.Fragment_Masseuse;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.DataHistory;
import com.example.khawoat_rmbp.well.Adapter.MassRecyclerView;


import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingMasseuse extends Fragment {

    private static final String URL_FROM_RECYCLERVIE_MYSERVICE = "http://203.158.131.67/~Adminwell/App/RecyclerView_myservice_Mass.php";
    private static final String URL_FROM_RECYCLERVIE_MYSERVICE_UPDATE = "http://203.158.131.67/~Adminwell/App/RecyclerView_myservice_Mass_Update.php";

    List<DataHistory> dataHistoryList;
    RecyclerView recyclerView;
    String IDMass;

    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;


    public PendingMasseuse() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View[] view = {inflater.inflate(R.layout.fragment_myservice_masseuse, null)};

        IDMass = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("IDMass" , "Null Value");//การรับค่า
        Toast.makeText(getContext(),IDMass,Toast.LENGTH_SHORT).show();

        Log.d("MS1",IDMass);
        getdatalist(IDMass);
        recyclerView = (RecyclerView) view[0].findViewById(R.id.recylcerView);
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

                view[0] = Recyclerview.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(view[0] !=null && gestureDetector.onTouchEvent(motionEvent)){
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(view[0]);
                    final String getId = dataHistoryList.get(RecyclerViewClickedItemPOS).getId();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("ยืนยันการทำงานของคุณ");
                    builder.setPositiveButton("เสร็จงาน", new DialogInterface.OnClickListener() {


                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //  Toast.makeText(getContext(),"คุณได้รับงานนี้แล้ว",Toast.LENGTH_SHORT).show();
                            selectAppovredmyservice(getId,"1");

                        }
                    });
                    builder.setNegativeButton("ไม่สำเร็จ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            selectAppovredmyservice(getId,"2");

                        }
                    });

                    builder.show();
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
        return view[0];


    }
///SHOW
    private void getdatalist(final String id) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_FROM_RECYCLERVIE_MYSERVICE , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataHistoryList = Arrays.asList(mGson.fromJson(response, DataHistory[].class));
                MassRecyclerView adapter = new MassRecyclerView(getContext(), dataHistoryList);
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
        // Adding request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

//// Update
    private void selectAppovredmyservice (final String id , final String choice){

        String cancel_reg_tag ="update";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FROM_RECYCLERVIE_MYSERVICE_UPDATE, new Response.Listener<String>() {
            public void onResponse(String response) {
                getdatalist(IDMass);
                recyclerView.setAdapter(mAdapter);

                if (response.equals("AP_Success")) {
                  //  Toast.makeText(getContext(), "คุณได้รับงานนี้แล้ว", Toast.LENGTH_SHORT).show();
                }else {
                   // Toast.makeText(getContext(), "ไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String, String>getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("Res_id", id);
                params.put("choice", choice);

                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getActivity()).addToRequestQueue(strReq, cancel_reg_tag);
        }

    }



