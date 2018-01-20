package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.khawoat_rmbp.well.Fragment_User.NotificationFragment;
import com.example.khawoat_rmbp.well.Fragment_User.RequestFragment;
import com.example.khawoat_rmbp.well.Fragment_User.ServiceFragment;
import com.example.khawoat_rmbp.well.Fragment_User.SettingFragment;
import com.example.khawoat_rmbp.well.R;

import java.util.ArrayList;

public class MainFragmentMasseuse extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;
    private FrameLayout frameLayout;
    private TextView mNavTv;

    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_masseuse);

        changeStatusBarColor();
        mNavTv= (TextView) findViewById(R.id.nav_tv);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        frameLayout= (FrameLayout) findViewById(R.id.layFrame);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RSUlight.ttf");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.serviceicon, "บริการ").setActiveColorResource(R.color.Bottom))
                .addItem(new BottomNavigationItem(R.drawable.requesticon, "งานของฉัน").setActiveColorResource(R.color.Bottom))
                .addItem(new BottomNavigationItem(R.drawable.notificationicon, "ข้อความ").setActiveColorResource(R.color.Bottom))
                .addItem(new BottomNavigationItem(R.drawable.settingicon, "ตั้งค่า").setActiveColorResource(R.color.Bottom))
                .setFirstSelectedPosition(0)
                .initialise();

//        fragments = getFragments();
//        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFCC00"));
        }
    }
//    private void setDefaultFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.add(R.id.layFrame, ServiceFragment.newInstance("บริการ"));
//        transaction.commit();
//    }
//    private ArrayList<Fragment> getFragments() {
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(ServiceFragment.newInstance("บริการ"));
//        fragments.add(RequestFragment.newInstance("งานของฉัน"));
//        fragments.add(NotificationFragment.newInstance("ข้อความ"));
//        fragments.add(SettingFragment.newInstance("ตั้งค่า"));
//        return fragments;
//    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
