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

public class NotificationFragment extends Fragment {

    public NotificationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String content) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RSUlight.ttf");
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("ข้อความข่าวสาร");
        mTitleBar.setLeftButtonImage(R.drawable.notificationicontitle,15,15);

        // Inflate the layout for this fragment
        return view;
    }

}
