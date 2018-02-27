package com.example.khawoat_rmbp.well.Reservation;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;

public class reservation extends AppCompatActivity {
    double minteger = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        changeStatusBarColor();

        TitleView mTitleBar = (TitleView) findViewById(R.id.titleBar);
        mTitleBar.setTitle("New Booking");
        mTitleBar.setLeftButtonImage(R.drawable.requesticontitle,15,15);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.Bottom));
        }
    }
}
