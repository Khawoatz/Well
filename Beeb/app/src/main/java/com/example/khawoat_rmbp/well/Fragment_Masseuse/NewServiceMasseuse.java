package com.example.khawoat_rmbp.well.Fragment_Masseuse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;


public class NewServiceMasseuse extends Fragment {


    public NewServiceMasseuse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_service_masseuse, container, false);
        // Inflate the layout for this fragment
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("งานใหม่");
        mTitleBar.setLeftButtonImage(R.drawable.incoming,15,15);
        return view;
    }

}
