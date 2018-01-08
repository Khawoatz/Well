package com.example.khawoat_rmbp.well.BannerSlider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.khawoat_rmbp.well.R;


public class SliderFragment extends Fragment {

    private static final String ARG_PARAM1 = "params";
    private String imageUrls;


    public SliderFragment() {
        // Required empty public constructor
    }

    public static SliderFragment newInstance(String param1) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageUrls = getArguments().getString(ARG_PARAM1);
        View view = inflater.inflate(R.layout.fragment_slider,container,false);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        Glide.with(getActivity()).load(imageUrls).into(img);
        return view;
    }
}
