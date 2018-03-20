package com.example.khawoat_rmbp.well.Fragment_Masseuse;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.DataHistory;
import com.example.khawoat_rmbp.well.Adapter.RecyclerViewAdapter;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.nanchen.titlebar.TitleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewServiceMasseuse extends Fragment {

    private static final String URL_FROM_RECYCLERVIE_NEWSERVICE = "http://203.158.131.67/~Adminwell/App/RecyclerView_Newmyservice_Mass.php";
    List<DataHistory> dataHistoryList;
    RecyclerView recyclerView;
    String IDMass;
    private Button bntYes,bntNo;

    private RecyclerView.Adapter mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_service_masseuse,  null);
        bntYes = (Button) view.findViewById(R.id.bntYes);
        bntNo = (Button) view.findViewById(R.id.bntNo);
        IDMass = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("IDMass" , "Null Value");//การรับค่า
        Log.d("MS1",IDMass);
        getdatalist(IDMass);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //initializing the dataHistoryList
        //dataHistoryList = new ArrayList<>();


        // JSON data web call function call from here.


        // Inflate the layout for this fragment
        return view;


    }



    private void getdatalist(final String id) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_FROM_RECYCLERVIE_NEWSERVICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataHistoryList = Arrays.asList(mGson.fromJson(response, DataHistory[].class));
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), dataHistoryList);
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

}
