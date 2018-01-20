package com.example.khawoat_rmbp.well.Fragment_User;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.khawoat_rmbp.well.BannerSlider.SliderFragment;
import com.example.khawoat_rmbp.well.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup_homeservice;
    private RadioButton rb_homeservice, rb_request, rb_notification, rb_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_fragment, null);

        initView(view);
        return view;
    }

    private void initView(View view){
        viewPager = (ViewPager) view.findViewById(R.id.Viewpager_container);
        Fragment fragment1 = new SliderFragment();
        Fragment fragment2 = new Fragment();
        Fragment fragment3 = new Fragment();
        Fragment fragment4 = new Fragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        viewPager.setAdapter(new PagerAdapter((getActivity()).getSupportFragmentManager(), fragmentList));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public PagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
}
