package com.example.khawoat_rmbp.well.Reservation;

import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;

public class reservation extends AppCompatActivity {
    double minteger = 0;
    private TextView Massage_Name;
    private String ms_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        changeStatusBarColor();

        Massage_Name = (TextView) findViewById(R.id.massage_name);

        ms_type = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("mstype","Null");
        Log.d("Sus",ms_type);
        if (ms_type.equals("1")){
            Massage_Name.setText("นวดประคบ");
        }
        else if (ms_type.equals("2")){
            Massage_Name.setText("นวดกดจุด");
        }
        else if (ms_type.equals("3")){
            Massage_Name.setText("นวดน้ำมัน");
        }
        else if (ms_type.equals("4")){
            Massage_Name.setText("นวดฝ่าเท้า");
        }
        else if (ms_type.equals("5")){
            Massage_Name.setText("นวดไมเกรน");
        }
        else if (ms_type.equals("6")){
            Massage_Name.setText("นวดสปอร์ต");
        }




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
