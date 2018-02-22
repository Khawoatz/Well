package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;

public class NotificationMasseuse extends Fragment {

    public NotificationMasseuse() {
        // Required empty public constructor
    }


    public static NotificationMasseuse newInstance(String content) {
        NotificationMasseuse fragment = new NotificationMasseuse();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_masseuse, container, false);
        // Inflate the layout for this fragment
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("ข้อความข่าวสาร");
        mTitleBar.setLeftButtonImage(R.drawable.notificationicontitle,15,15);
        return view;
    }

}
