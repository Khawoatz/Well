package com.example.khawoat_rmbp.well.Fragment_User;


import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.RecycelViewUser.DataRecycelViewUser.DataHistoryUser;
import com.example.khawoat_rmbp.well.RecycelViewUser.UserPendingRecycelView;
import com.example.khawoat_rmbp.well.RecycelViewUser.UserSuccRecyclerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingWork extends Fragment {


    private static final String URL_FROM_RECYCLERVIE_SUCCESS_USER = "http://203.158.131.67/~Adminwell/App/RecyclerView_Success_User.php";

    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    List<DataHistoryUser> dataHistoryUserList;
    RecyclerView recyclerView;
    String IDCus;

    public PendingWork() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_pending_work, container, false);
        final View[] view = {inflater.inflate(R.layout.fragment_pending_work, null)};
        IDCus = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Cus_id", "Null Value");//การรับค่า

        Log.d("asas", IDCus.toString());
        Toast.makeText(getContext(), IDCus, Toast.LENGTH_SHORT).show();
        Log.d("M7", IDCus);
        getdatalist(IDCus);
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
                    String getIdd = dataHistoryUserList.get(RecyclerViewClickedItemPOS).getId();
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

        return view[0];
    }
    private void getdatalist(final String id) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_FROM_RECYCLERVIE_SUCCESS_USER, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("LatResponse:", response.toString());

                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                dataHistoryUserList = Arrays.asList(mGson.fromJson(response, DataHistoryUser[].class));
             //   UserSuccRecyclerView adapter = new UserSuccRecyclerView(getContext(), dataHistoryUserList);
                UserPendingRecycelView adapter = new UserPendingRecycelView (getContext(), dataHistoryUserList);
                recyclerView.setAdapter(adapter);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            //ส่งค่าไป php
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("CUS_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        AppSingleton.getInstance(getContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

}
