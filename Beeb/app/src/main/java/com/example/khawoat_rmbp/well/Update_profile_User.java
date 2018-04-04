package com.example.khawoat_rmbp.well;

import android.app.AlertDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khawoat_rmbp.well.Fragment_Masseuse.SettingMasseuse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.getName;

public class Update_profile_User extends AppCompatActivity {

    private static final String URL_FOR_UPDATE_SELECT_PFOFILE = "http://203.158.131.67/~Adminwell/App/update_select_profile.php";
    private static final String URL_FOR_UPDATE_DATE_PROFILE = "http://203.158.131.67/~Adminwell/App/update_update_profile.php";
    private EditText edName,edSurname,edEmail,edTel;

    private ViewPager viewPager;
    public static final String JSON_ARRAY = "result";
    private JSONArray result;
    private ArrayList<String> arrayList;
    private Button btnSubmit;
    private String IDMass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile__user);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edName = (EditText) findViewById(R.id.edName);
        edSurname =(EditText) findViewById(R.id.edSurname);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edTel = (EditText) findViewById(R.id.edTel);
        IDMass = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("IDMass" , "Null Value");//การรับค่า
        Log.d("IDmassupdate",IDMass);
        selectDetailGatage(IDMass);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ResultUpdate", edName.getText().toString());
//                Log.d("ResultUpdate1", edSurname.getText().toString());
//                Log.d("ResultUpdate2", edEmail.getText().toString());
//                Log.d("ResultUpdate3", edTel.getText().toString());
                UpdateProfile(IDMass,edName.getText().toString(),edSurname.getText().toString(),edEmail.getText().toString(),edTel.getText().toString());

            }
        });




    }

    private void selectDetailGatage(final String id) {
        // Tag used to cancel the request
        String cancel_req_tag = "select";
        //progressDialog.setMessage("Loading....");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_UPDATE_SELECT_PFOFILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    String Name = jObj.getString("Name");
                    String Surname = jObj.getString("Surname");
                    String Email = jObj.getString("Email");
                    String Tel = jObj.getString("Telephone");
                    Log.d("lol",Name);

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
        // Adding request to request queue
        AppSingleton.getInstance(this).addToRequestQueue(strReq, cancel_req_tag);
    }
/////update profile///
    private void UpdateProfile( final String id,final String name, final String surname, final String email, final String tele) {
        // Tag used to cancel the request
        String cancel_req_tag = "update";
        //progressDialog.setMessage("Loading....");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_UPDATE_DATE_PROFILE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Result234", response.toString());
                if (response.equals("FALSE ")) {
                   // Toast.makeText(getApplicationContext(), "เปลี่ยนรหัสสำเร็จ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "แก้ไขโปรไฟล์สำเร็จ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),SettingMasseuse.class);
                    startActivity(i);
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
                params.put("MS_ID", id);
                params.put("NameMass", name);
                params.put("SurnameMass", surname);
                params.put("EmailMass", email);
                params.put("TelMass", tele);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน

                //Log.d("Selectgarage Map: ", String.valueOf(id));
                return params;
            }

        };
        // Adding request to request queue
        AppSingleton.getInstance(this).addToRequestQueue(strReq, cancel_req_tag);
    }


}


