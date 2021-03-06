package com.example.khawoat_rmbp.well.Fragment_Masseuse;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.RecyclerView.DataRecyclerView.DataHistory;
import com.example.khawoat_rmbp.well.RecyclerView.RecyclerViewAdapter;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.BannerSlider.SliderFragment;
import com.example.khawoat_rmbp.well.Fragment_User.ServiceFragment;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewServiceMasseuse extends Fragment {


    private static final String URL_FROM_RECYCLERVIE_NEWSERVICE = "http://203.158.131.67/~Adminwell/App/RecyclerView_Newmyservice_Mass.php";
    private static final String URL_FROM_RECYCLERVIE_NEWSERVICE_UPDATE = "http://203.158.131.67/~Adminwell/App/RecyclerView_Newmyservice_Mass_Update.php";
    ViewPager viewPager;

    List<DataHistory> dataHistoryList;
     RecyclerView recyclerView;
    String IDMass;
    String getIdCus;
    private Button bntYes,bntNo;

    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_service_masseuse, null);
//         View[] view = {inflater.inflate(R.layout.fragment_new_service_masseuse, null)};

        IDMass = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("IDMass" , "Null Value");//การรับค่า
        Toast.makeText(getContext(),IDMass,Toast.LENGTH_SHORT).show();

        Log.d("MS1",IDMass);
        getdatalist(IDMass);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerView);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView  Recyclerview, MotionEvent motionEvent) {

                View view = Recyclerview.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(view !=null && gestureDetector.onTouchEvent(motionEvent)){
                    //Getting RecyclerView Clicked item value.
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(view);
                    //Printing RecyclerView Clicked item clicked value using Toast Message./
                    //Toast.makeText(ZListActivity.this, getDataAdapterList.get(RecyclerViewClickedItemPOS).getZdepth()), Toast.LENGTH_LONG).show();*/
                    //Toast.makeText(MainActivity.this, getDataAdapterTels.get(RecyclerViewClickedItemPOS).getTelname(), Toast.LENGTH_SHORT).show();
                    //Êè§¤èÒ ID ä»·ÕèË¹éÒ detailfield
                    final String getId = dataHistoryList.get(RecyclerViewClickedItemPOS).getId();
                    getIdCus = dataHistoryList.get(RecyclerViewClickedItemPOS).getIdCus();
                    Log.d("Cus_is",getIdCus);
                  //  Toast.makeText(getContext(),getId,Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("คุณต้องการรับงานหรือไม่");

                    builder.setPositiveButton("รับ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                          //  Toast.makeText(getContext(),"คุณได้รับงานนี้แล้ว",Toast.LENGTH_SHORT).show();
                            selectAppovred(getId,"1");
                        }
                    });
                    builder.setNegativeButton("ไม่รับ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selectAppovred(getId,"2");

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

        //dataHistoryList = new ArrayList<>();


        // JSON data web call function call from here.


        // Inflate the layout for this fragment
        initView(view);
        return view;


    }
    private void initView(View view){
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        Fragment fragment1 = new NewServiceMasseuse();
        Fragment fragment2 = new MyserviceMasseuse();
        Fragment fragment3 = new NotificationMasseuse();
        Fragment fragment4 = new SettingMasseuse();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        viewPager.setAdapter(new PagerAdapter((getActivity()).getSupportFragmentManager(), fragmentList));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public PagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
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
    private void pushNotificationQueueReserve(final String id,final String noti) {
        // Tag used to cancel the request
        String cancel_req_tag = "deleteQueue";
        //progressDialog.setMessage("Loading....");
        //showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, "http://203.158.131.67/~Adminwell/App/push_notification_reserve_queue_user2.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Reponse",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cus_id", id);
                params.put("Notification", noti);
                Log.d("Noti Map: ", String.valueOf(id));
                return params;
            }

        };
        // Adding request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
    }
///update
    private void selectAppovred (final String id , final String choice){
        String cancel_reg_tag ="update";

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FROM_RECYCLERVIE_NEWSERVICE_UPDATE, new Response.Listener<String>() {
            public void onResponse(String response) {

                Log.d("response123",response.toString());
                getdatalist(IDMass);
                recyclerView.setAdapter(mAdapter);
               // Toast.makeText(getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                if (response.equals("\r\nAP_Success")) {
                    pushNotificationQueueReserve(getIdCus,"พนักงานรับการจองเรียบร้อยแล้ว");
                    Toast.makeText(getContext(), "คุณได้รับงานนี้แล้ว", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "ไม่สำเร็จ", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
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
