package com.example.khawoat_rmbp.well.Fragment_User;

import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.MyFragmentAdapter;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends FragmentActivity {

    private static final String URL_FROM_INSERT_TOKEN = "http://203.158.131.67/~Adminwell/App/Insert_Token_User.php";
    private static final String URL_FROM_CHECK_TOKEN = " http://203.158.131.67/~Adminwell/App/check-token_user.php";
    private static final String URL_FROM_SELECT_TOKEN = " http://203.158.131.67/~Adminwell/App/select-token_user.php";
    private static final String URL_FROM_UPDATE_TOKEN = " http://203.158.131.67/~Adminwell/App/update-token_user.php";



    private String token;
    private String IDCus;

    private RadioGroup radioGroup;
    private RadioButton rb_service, rb_request,rb_notification,rb_setting;
    private ViewPager scrollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        IDCus = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Cus_id", "Null Value");

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d("testtoke",token);
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("TOKEN",token).commit();


        Log.d("tokenja", token);
        checktoken(IDCus);
        changeStatusBarColor();
        initView();

    }

    private void checktoken(final String IDCus) {
        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_CHECK_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("logreponscheck",response.toString());
                if (response.toString().equals("\r\nfalse")){
                    InsertToken(token,IDCus);
                    Log.d("ghghghghghgh",IDCus+token);

                }else {
                    selectToken(IDCus);
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
                params.put("Cus_id", IDCus);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                Log.d("select Map: ", String.valueOf(IDCus));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

    private void selectToken(final String IDCus) {
        // Tag used to cancel the request
        String cancel_req_tag = "garage";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_SELECT_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Token Response Select: ", response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    String tokenDB = jObj.getString("Token");
                    String tokenApp = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("token", "Null Value");
                    Log.d("tokenDB",tokenDB);
                    if (tokenDB.equals(tokenApp)){
                        Log.d("CheckToken","Success");
                        Log.d("token2","token "+tokenApp);
                    }else if (tokenDB.equals("")){
                        updateToken(tokenApp,IDCus);
                    }

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
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cus_id", IDCus);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

    private void updateToken(final String token,final String IDCus) {
        // Tag used to cancel the request
        String cancel_req_tag = "garage";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_UPDATE_TOKEN, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                Log.d("Token Response update: ", response.toString());


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

                params.put("Token", token);
                params.put("Cus_id", IDCus);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);
    }

    private void InsertToken(final String token,final String IDCus) {

        final String cancel_req_tag = "listview";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FROM_INSERT_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("logreponsinsert",response.toString());

                if (response.toString().equals("false")){

                }else {

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

                params.put("Token", token);
                params.put("ID_CUS", IDCus);//  ชื่อซ้ายตรงกับ php ชื่อขวาตรงกับข้างบน
                Log.d("selectMap55555: ", String.valueOf(IDCus));
                Log.d("selectMaptttttt: ", String.valueOf(token));
                return params;
            }
        };
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, cancel_req_tag);

    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFCC00"));
        }
    }

    private void initView(){
        // RadioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_service = (RadioButton) findViewById(R.id.rb_homeservice);
        rb_request = (RadioButton) findViewById(R.id.rb_request);
        rb_notification = (RadioButton) findViewById(R.id.rb_notification);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_homeservice:
                        scrollViewPager.setCurrentItem(0,false);
                        break;

                    case R.id.rb_request:
                        scrollViewPager.setCurrentItem(1,false);
                        break;

                    case R.id.rb_notification:
                        scrollViewPager.setCurrentItem(2,false);
                        break;

                    case R.id.rb_setting:
                        scrollViewPager.setCurrentItem(3,false);
                        break;

                }
            }
        });

        scrollViewPager = (ViewPager) findViewById(R.id.scrollPages);
        Fragment fragment1 = new ServiceFragment();
        Fragment fragment2 = new RequestFragment();
        Fragment fragment3 = new NotificationFragment();
        Fragment fragment4 = new SettingFragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        scrollViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragmentList));
        scrollViewPager.setCurrentItem(0,false);
        scrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainFragment.this, "หน้าแรก", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_homeservice);
                        break;
                    case 1:
                        Toast.makeText(MainFragment.this, "งานของฉัน", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_request);
                        break;
                    case 2:
                        Toast.makeText(MainFragment.this, "ข้อความ", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_notification);
                        break;
                    case 3:
                        Toast.makeText(MainFragment.this, "ตั้งค่า", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_setting);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}