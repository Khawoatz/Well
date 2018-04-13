package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.LoginMasseuse;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.Update_profile_User;
import com.nanchen.titlebar.TitleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SettingMasseuse extends Fragment implements CompoundButton.OnCheckedChangeListener {


    private static final String URL_ONOFF_SWITCH ="http://203.158.131.67/~Adminwell/App/Switch_OnOff_Mass.php";
    //private static final String URL_SELECT_ONOFF_SWITCH ="http://203.158.131.67/~Adminwell/App/Switch_Select_OnOff_Mass.php";
    private Button btnEditprofile,btnAboutapp,btnContactus,btnLogout;
    private RatingBar rb;
    Switch mySwitch = null;
    private String IDMass;

    public SettingMasseuse() {
        // Required empty public constructor
    }


    public static SettingMasseuse newInstance(String content) {
        SettingMasseuse fragment = new SettingMasseuse();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_masseuse, container, false);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RSUlight.ttf");
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("ตั้งค่า");
        mTitleBar.setLeftButtonImage(R.drawable.settingicontitle,15,15);

        IDMass = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("IDMass" , "Null Value");//การรับค่า
        Log.d("MS1",IDMass);

       mySwitch = (Switch) view.findViewById(R.id.switch1);
       mySwitch.setOnCheckedChangeListener(this);



        btnEditprofile = (Button) view.findViewById(R.id.btnEditprofile);
        btnAboutapp = (Button) view.findViewById(R.id.btnAboutapp);
        btnContactus = (Button) view.findViewById(R.id.btnContactus);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        btnEditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingMasseuse.this.getActivity(), Update_profile_User.class);
                startActivity(i);
            }
        });

        btnContactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://line.me/R/ti/p/%40and3482j"));
              //  ntent.setData(Uri.parse("market://details?id=com.example.android"));

                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LoginMasseuse.class);
                startActivity(i);
                getActivity().finish();

            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    private void updateSwitch(final String id , final String choice) {
        String cancel_reg_tag ="update";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_ONOFF_SWITCH, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.d("response123", response.toString());
                if (response.equals("On_Success")) {

                    Toast.makeText(getContext(), "คุณได้ปิดระบบทำการ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "ไม่สำเร็จ", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("MS_ID", id);
                params.put("choice", choice);

                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getActivity()).addToRequestQueue(strReq, cancel_reg_tag); }




        @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
               // selectOn(getId,"1");
            updateSwitch (IDMass,"1");
        }else {
            updateSwitch (IDMass,"2");
        }
    }
}
