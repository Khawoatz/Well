package com.example.khawoat_rmbp.well.Fragment_User;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.khawoat_rmbp.well.BannerSlider.SliderFragment;
import com.example.khawoat_rmbp.well.LoginUser;
import com.example.khawoat_rmbp.well.MainActivity;
import com.example.khawoat_rmbp.well.Massage_Service.Acupunc_Massage;
import com.example.khawoat_rmbp.well.Massage_Service.Foot_Massage;
import com.example.khawoat_rmbp.well.Massage_Service.Herbal_Massage;
import com.example.khawoat_rmbp.well.Massage_Service.Migraine_Massage;
import com.example.khawoat_rmbp.well.Massage_Service.Oil_Massage;
import com.example.khawoat_rmbp.well.Massage_Service.Sport_Massage;
import com.example.khawoat_rmbp.well.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup_homeservice;
    private RadioButton rb_homeservice, rb_request, rb_notification, rb_setting;
    private ImageView btHerball,btAcupuncc,btOill,btFoott,btMigrainee,btSportt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_fragment, null);

        btHerball = (ImageView) view.findViewById(R.id.btHerbal);
        btAcupuncc = (ImageView) view.findViewById(R.id.btAcupunc);
        btOill = (ImageView) view.findViewById(R.id.btOil);
        btFoott = (ImageView) view.findViewById(R.id.btFoot);
        btMigrainee = (ImageView) view.findViewById(R.id.btMigraine);
        btSportt = (ImageView) view.findViewById(R.id.btSport);

        btHerball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Herbal_Massage.class);
                startActivity(i);
            }
        });

        btAcupuncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Acupunc_Massage.class);
                startActivity(i);
            }
        });
        btOill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Oil_Massage.class);
                startActivity(i);
            }
        });
        btFoott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Foot_Massage.class);
                startActivity(i);
            }
        });
        btMigrainee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Migraine_Massage.class);
                startActivity(i);
            }
        });
        btSportt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceFragment.this.getActivity(),Sport_Massage.class);
                startActivity(i);
            }
        });
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
