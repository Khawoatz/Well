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
import com.example.khawoat_rmbp.well.RecycelViewUser.DataRecycelViewUser.DataHistoryUser;
import com.example.khawoat_rmbp.well.RecycelViewUser.UserSuccRecyclerView;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessWork extends Fragment {

    private static final String URL_FROM_RECYCLERVIE_SUCCESS_USER = "http://203.158.131.67/~Adminwell/App/RecyclerView_Success_User.php";
    private static final String URL_FROM_DILOG = "http://203.158.131.67/~Adminwell/App/Dialog_success_shownamemass.php";
    private static final String URL_FROM_DILOG_REVIEW = "http://203.158.131.67/~Adminwell/App/Dialog_success_review_mass_User.php";


    List<DataHistoryUser> dataHistoryUserList;
    RecyclerView recyclerView;
    String IDCus;

    private RecyclerView.Adapter mAdapter;
    private int RecyclerViewClickedItemPOS;
    private TextView tvnameMass,tvst;
    private RatingBar Rbar;
    private EditText etcomment;
    private Button btnsudmit;
    private String NameMass;
    private float Rating123;
    private String Idmass;

    public SuccessWork() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View[] view = {inflater.inflate(R.layout.fragment_success_work, null)};
        IDCus = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Cus_id" , "Null Value");//การรับค่า

        Log.d("asas",IDCus.toString());
        Toast.makeText(getContext(),IDCus,Toast.LENGTH_SHORT).show();
        Log.d("M7",IDCus);
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
                    Log.d("abcdll",getIdd);
                    //Aler Dialog
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog_review_mass);
//
                    tvnameMass = (TextView) dialog.findViewById(R.id.tvnameMass);
                    tvst = (TextView) dialog.findViewById(R.id.tvst);
                    Rbar = (RatingBar) dialog.findViewById(R.id.Rbar);
                    etcomment = (EditText) dialog.findViewById(R.id.etcomment);
//
                    btnsudmit = (Button) dialog.findViewById(R.id.btnsudmit);
                  Toast.makeText(getContext(),"sssssssssssss",Toast.LENGTH_SHORT).show();
            //       Log.d("abcdll",getId);
                    showdialogname(getIdd);
                    Rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //   Toast.makeText(getContext(),  rating, Toast.LENGTH_SHORT).show();
                         //   tvst.setText("Your Rating : " + rating);


                            Rating123 = rating;



                        }
                    });

                    btnsudmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            tvst.setText("Your Rating : " + Rating123);
                           // Toast.makeText(getContext(),  Rating123, Toast.LENGTH_SHORT).show();

                            String strStar = String.valueOf(Rating123);
//                            updatereview(IDCus,etcomment.getText().toString(),strStar,Idmass);
                            updatereview(Idmass,etcomment.getText().toString(),IDCus,strStar);
                            dialog.dismiss();
                        }
                    });


                    dialog.show();
                    //Log.d("abcdll",getId.toString());
//
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
///insert review
    private void updatereview(final String id,final String commentt,final String idcustomer,final String pointt)  {
        // Tag used to cancel the request
        String cancel_req_tag = "update";
        //progressDialog.setMessage("Loading....");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FROM_DILOG_REVIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                params.put("MS_ID", id);

                params.put("Comment", commentt);
                params.put("CUS_ID", idcustomer);

                params.put("Pointss", pointt);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน



                Log.d("dsgserter: ", String.valueOf(commentt));
                //Log.d("Selectgarage Map: ", String.valueOf(id));
                return params;
            }

        };
        // Adding request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
    }{
    }

    ///show dialog
    private void showdialogname(final String id) {
        String cancel_reg_tag ="showdialog";
        final StringRequest strReq = new StringRequest(Request.Method.POST, URL_FROM_DILOG, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("S123", response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    NameMass = jObj.getString("namemass");
                    Idmass = jObj.getString("Masseuse_id");
                    Log.d("name1234",NameMass.toString());
                    tvnameMass.setText(NameMass);

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "enddddddddddddddddd", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
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
                params.put("CUS_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                //Log.d("select Map: ", String.valueOf(id));
                return params;
        }
    };
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_reg_tag);
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
                UserSuccRecyclerView adapter = new UserSuccRecyclerView(getContext(), dataHistoryUserList);
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
