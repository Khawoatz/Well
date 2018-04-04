package com.example.khawoat_rmbp.well;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jah on 3/16/2018.
 */

public class Update_profle_user2 extends AppCompatActivity {

    private static final String URL_FOR_UPDATE_SELECT_PFOFILE_USER = "http://203.158.131.67/~Adminwell/App/update_select_profile_user.php";
    //private static final String URL_FOR_UPDATE_DATE_PROFILE_USER = "http://203.158.131.67/~Adminwell/App/update_update_profile_user.php";
    private EditText edName,edSurname,edEmail,edTel;

    private ViewPager viewPager;
    public static final String JSON_ARRAY = "result";
    private JSONArray result;
    private ArrayList<String> arrayList;
    private Button btnSubmit;
    private String Cus_id;

    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profle_user2);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edName = (EditText) findViewById(R.id.edName);
        edSurname =(EditText) findViewById(R.id.edSurname);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edTel = (EditText) findViewById(R.id.edTel);

        Cus_id = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Cus_id" , "Null Value");//การรับค่า
        Log.d("IDcutupdate",Cus_id);
        selectDetailGatage(Cus_id);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ResultUpdate", edName.getText().toString());
//                Log.d("ResultUpdate1", edSurname.getText().toString());
//                Log.d("ResultUpdate2", edEmail.getText().toString());
//                Log.d("ResultUpdate3", edTel.getText().toString());
//                UpdateProfilUserIDCut,edName.getText().toString(),edSurname.getText().toString(),edEmail.getText().toString(),edTel.getText().toString();

            }
        });

    }

    private void selectDetailGatage(final String id) {

        String cancel_req_tag = "select";

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_UPDATE_SELECT_PFOFILE_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    String Name = jObj.getString("Name");
                    String Surname = jObj.getString("Surname");
                    String Email = jObj.getString("Email");
                    String Tel = jObj.getString("Telephone");

//          ส่งค่าไปหน้าอื่น              PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("sGaragename", Garagename).commit();
//                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("sAddress", Address).commit();
//                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("sLatitude", Latitude).commit();


                    edName.setText(Name);
                    edSurname.setText(Surname);
                    edEmail.setText(Email);
                    edTel.setText(Tel);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            //ส่งค่าไป php
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("CT_ID", id);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                //Log.d("select Map: ", String.valueOf(id));
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(this).addToRequestQueue(strReq, cancel_req_tag);
    }

}
