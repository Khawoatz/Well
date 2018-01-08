package com.example.khawoat_rmbp.well.Fragment_User;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.khawoat_rmbp.well.BannerSlider.SliderFragment;
import com.example.khawoat_rmbp.well.BannerSlider.SliderIndicator;
import com.example.khawoat_rmbp.well.BannerSlider.SliderPagerAdapter;
import com.example.khawoat_rmbp.well.BannerSlider.SliderView;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.SliderUtils;
import com.example.khawoat_rmbp.well.ViewPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;
    private FrameLayout frameLayout;
    private TextView mNavTv;
    private Fragment mContent;
    private int dotscount;
    private ImageView[] dots;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    RequestQueue rq;
    List<SliderUtils> sliderImg;
    ViewPagerAdapter viewPagerAdapter;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    String request_url = "http://203.158.131.67/~Adminwell/sliderjsonoutput.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        changeStatusBarColor();

        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        setupSlider();

        rq = Volley.newRequestQueue(this);
        sliderImg = new ArrayList<>();
//        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mNavTv= (TextView) findViewById(R.id.nav_tv);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        frameLayout= (FrameLayout) findViewById(R.id.layFrame);


//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new MyTimerTaskBanner(),2000,4000);

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

        fragments = getFragments();
        setDefaultFragment();
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
    public class MyTimerTaskBanner extends TimerTask{

        @Override
        public void run() {
            MainFragment.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void setupSlider(){
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SliderFragment.newInstance("http://203.158.131.67/~Adminwell/BannerSlide/slide1.png"));
        fragments.add(SliderFragment.newInstance("http://203.158.131.67/~Adminwell/BannerSlide/slide2.png"));
        fragments.add(SliderFragment.newInstance("http://203.158.131.67/~Adminwell/BannerSlide/slide3.png"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(),fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this,mLinearLayout,sliderView,R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }


    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layFrame, ServiceFragment.newInstance("บริการ"));
        transaction.commit();
    }
    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ServiceFragment.newInstance("บริการ"));
        fragments.add(RequestFragment.newInstance("งานของฉัน"));
        fragments.add(NotificationFragment.newInstance("แจ้งเตือน"));
        fragments.add(SettingFragment.newInstance("ตั้งค่า"));
        return fragments;
    }
    @Override
    public void onTabSelected(int position) {

        if (position < fragments.size()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            //当前的fragment
            Fragment from = fm.findFragmentById(R.id.layFrame);

            //点击即将跳转的fragment
            Fragment to = fragments.get(position);
            if (to.isAdded()) {
                ft.hide(from).show(to);
            } else {
                ft.hide(from).add(R.id.layFrame,to);
                if (to.isHidden()) {
                    ft.show(to);
                    Log.d("----------------","被隐藏了");
                    Log.d("----------------","为什么这个没有添加到GitHub上去");

                }
            }

            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabReselected(int position) {

    }
    public void onBackPressed(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setIcon(R.drawable.logowell4);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit ?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                finish();
                System.exit(0);
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}