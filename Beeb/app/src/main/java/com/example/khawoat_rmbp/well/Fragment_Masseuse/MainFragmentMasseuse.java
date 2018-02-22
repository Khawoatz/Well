package com.example.khawoat_rmbp.well.Fragment_Masseuse;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.khawoat_rmbp.well.Adapter.MyFragmentAdapter;
import com.example.khawoat_rmbp.well.Fragment_User.MainFragment;
import com.example.khawoat_rmbp.well.Fragment_User.NotificationFragment;
import com.example.khawoat_rmbp.well.Fragment_User.RequestFragment;
import com.example.khawoat_rmbp.well.Fragment_User.ServiceFragment;
import com.example.khawoat_rmbp.well.Fragment_User.SettingFragment;
import com.example.khawoat_rmbp.well.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentMasseuse extends FragmentActivity {

    private RadioGroup radioGroup;
    private RadioButton rb_service, rb_request,rb_notification,rb_setting;
    private ViewPager scrollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_masseuse);

        changeStatusBarColor();
        initView();
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

    private void initView(){
        // RadioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_service = (RadioButton) findViewById(R.id.rb_homeservice);
        rb_request = (RadioButton) findViewById(R.id.rb_request);
        rb_notification = (RadioButton) findViewById(R.id.rb_notification);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_homeservice:
                        scrollViewPager.setCurrentItem(0,false);
                        break;

                    case R.id.rb_request:
                        scrollViewPager.setCurrentItem(1,false);
                        break;

                    case R.id.rb_notification:
                        scrollViewPager.setCurrentItem(2,false);
                        break;

                    case R.id.rb_setting:
                        scrollViewPager.setCurrentItem(3,false);
                        break;

                }
            }
        });

        scrollViewPager = (ViewPager) findViewById(R.id.scrollPages);
        Fragment fragment1 = new NewServiceMasseuse();
        Fragment fragment2 = new MyserviceMasseuse();
        Fragment fragment3 = new NotificationMasseuse();
        Fragment fragment4 = new SettingMasseuse();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        scrollViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragmentList));
        scrollViewPager.setCurrentItem(0,false);
        scrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainFragmentMasseuse.this, "หน้าแรก", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_homeservice);
                        break;
                    case 1:
                        Toast.makeText(MainFragmentMasseuse.this, "งานของฉัน", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_request);
                        break;
                    case 2:
                        Toast.makeText(MainFragmentMasseuse.this, "ข้อความ", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_notification);
                        break;
                    case 3:
                        Toast.makeText(MainFragmentMasseuse.this, "ตั้งค่า", Toast.LENGTH_SHORT).show();
                        radioGroup.check(R.id.rb_setting);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
