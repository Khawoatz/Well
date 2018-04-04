package com.example.khawoat_rmbp.well.Reservation;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.Adapter.CardFragmentPagerAdapter;
import com.example.khawoat_rmbp.well.Adapter.ShadowTransformer;
import com.example.khawoat_rmbp.well.R;

public class Reservation_ChooseMass extends AppCompatActivity {

    private TextView tvMassage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__choose_mass);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        LinearLayout llSearchMass = (LinearLayout) findViewById(R.id.ll_searchMass);
        llSearchMass.setVisibility(View.INVISIBLE);
        LinearLayout llChooseMass = (LinearLayout) findViewById(R.id.ll_ChooseMass);
        llChooseMass.setVisibility(View.VISIBLE);


        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
    }

    /**
     * Change value in dp to pixels
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
