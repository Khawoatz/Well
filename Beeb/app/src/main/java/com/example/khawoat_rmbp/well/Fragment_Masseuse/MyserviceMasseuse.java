package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khawoat_rmbp.well.Adapter.SectionsPageAdapter;
import com.example.khawoat_rmbp.well.Fragment_User.PendingWork;
import com.example.khawoat_rmbp.well.Fragment_User.SuccessWork;
import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;


public class MyserviceMasseuse extends  Fragment{

    private ViewPager mViewPager;

    public MyserviceMasseuse () {
        // Required empty public constructor
    }


    public static MyserviceMasseuse newInstance(String content) {
        MyserviceMasseuse fragment = new MyserviceMasseuse();
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myservice_masseuse, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerTab);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLay);
        tabLayout.setupWithViewPager(mViewPager);
        // Inflate the layout for this fragment
        TitleView mTitleBar = (TitleView) view.findViewById(R.id.titleBar);
        mTitleBar.setTitle("งานของฉัน");
        mTitleBar.setLeftButtonImage(R.drawable.notificationicontitle,15,15);
        return view;
    }
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new PendingMasseuse(),"งานที่ดำเนินการอยู่");
        adapter.addFragment(new SuccessMasseuse(),"ประวัติงาน");
        viewPager.setAdapter(adapter);
    }
}
