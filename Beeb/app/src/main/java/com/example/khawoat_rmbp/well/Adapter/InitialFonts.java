package com.example.khawoat_rmbp.well.Adapter;

import android.app.Application;

import com.example.khawoat_rmbp.well.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by KHAWOAT-rMBP on 27/4/2018 AD.
 */

public class InitialFonts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/RSUlight.ttf")
//        .setFontAttrId(R.attr.fonts)
        .build());
    }
}
