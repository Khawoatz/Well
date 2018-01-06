package com.example.khawoat_rmbp.well.Fragment_User;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.ViewPagerAdapter;

public class ServiceFragment extends Fragment {

    private ViewPager viewPager;

    public ServiceFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

//        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);


        return inflater.inflate(R.layout.activity_service_fragment, container, false);

    }
    public static ServiceFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ServiceFragment fragment = new ServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
