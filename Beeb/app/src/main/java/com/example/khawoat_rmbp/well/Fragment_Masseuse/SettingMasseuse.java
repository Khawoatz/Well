package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;


public class SettingMasseuse extends Fragment {


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

        // Inflate the layout for this fragment
        return view;
    }
}
