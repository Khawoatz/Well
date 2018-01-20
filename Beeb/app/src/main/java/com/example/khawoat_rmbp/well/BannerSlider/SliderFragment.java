package com.example.khawoat_rmbp.well.BannerSlider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khawoat_rmbp.well.R;

import java.util.ArrayList;
import java.util.List;


public class SliderFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private SliderView mViewPager;
    private TextView mStartView;
    private List<String> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        mViewPager = (SliderView) view.findViewById(R.id.viewPager);
//        mStartView = (TextView) view.findViewById(R.id.Start_Word) ;
        init();
        return view;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    private void init(){
        mList = new ArrayList<>();
        mList.add("http://203.158.131.67/~Adminwell/BannerSlide/slide1.png");
        mList.add("http://203.158.131.67/~Adminwell/BannerSlide/slide2.png");
        mList.add("http://203.158.131.67/~Adminwell/BannerSlide/slide3.png");
        mViewPager.setData(mList);

//        mStartView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onclick start");
//                Intent intent = new Intent(getActivity(), SelfTestActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });
    }


//    private static final String ARG_PARAM1 = "params";
//    private String imageUrls;
//
//
//    public SliderFragment() {
//        // Required empty public constructor
//    }
//
//    public static SliderFragment newInstance(String param1) {
//        SliderFragment fragment = new SliderFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        imageUrls = getArguments().getString(ARG_PARAM1);
//        View view = inflater.inflate(R.layout.fragment_slider,container,false);
//        ImageView img = (ImageView) view.findViewById(R.id.img);
//        Glide.with(getActivity()).load(imageUrls).into(img);
//        return view;
//    }
}
