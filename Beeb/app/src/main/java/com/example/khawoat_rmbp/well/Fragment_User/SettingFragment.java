package com.example.khawoat_rmbp.well.Fragment_User;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khawoat_rmbp.well.Fragment_Masseuse.SettingMasseuse;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.Update_profile_User;
import com.example.khawoat_rmbp.well.Update_profle_user2;
import com.nanchen.titlebar.TitleView;

public class SettingFragment extends Fragment {


    private Button btnContactus,btnEditprofile;


    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(String content) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString("ARGST", content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("ตั้งค่า");
        mTitleBar.setLeftButtonImage(R.drawable.settingicontitle,15,15);

        btnContactus = (Button) view.findViewById(R.id.btnContactus);
        btnEditprofile = (Button) view.findViewById(R.id.btnEditprofile);

        btnEditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingFragment.this.getActivity(), Update_profle_user2.class);
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

        // Inflate the layout for this fragment
        return view;
    }



}
