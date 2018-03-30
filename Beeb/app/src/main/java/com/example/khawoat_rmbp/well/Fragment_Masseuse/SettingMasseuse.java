package com.example.khawoat_rmbp.well.Fragment_Masseuse;

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
import android.widget.RatingBar;

import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.Update_profile_User;
import com.nanchen.titlebar.TitleView;

import java.util.Set;


public class SettingMasseuse extends Fragment {


    private Button btnEditprofile,btnAboutapp,btnContactus;
    private RatingBar rb;

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

        btnEditprofile = (Button) view.findViewById(R.id.btnEditprofile);
        btnAboutapp = (Button) view.findViewById(R.id.btnAboutapp);
        btnContactus = (Button) view.findViewById(R.id.btnContactus);
        rb = (RatingBar) view.findViewById(R.id.rb);
        rb.getVisibility();

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

        // Inflate the layout for this fragment
        return view;
    }
}
