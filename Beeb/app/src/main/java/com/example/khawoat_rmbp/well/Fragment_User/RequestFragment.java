package com.example.khawoat_rmbp.well.Fragment_User;

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

public class RequestFragment extends Fragment {


    public RequestFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RequestFragment newInstance(String content) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RSUlight.ttf");
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("รายการของฉัน");
        mTitleBar.setLeftButtonImage(R.drawable.requesticontitle,15,15);

        // Inflate the layout for this fragment
        return view;
    }

}
