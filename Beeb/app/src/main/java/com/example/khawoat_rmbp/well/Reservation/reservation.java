package com.example.khawoat_rmbp.well.Reservation;

import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;
import com.nanchen.titlebar.TitleView;

public class reservation extends AppCompatActivity {
    double minteger = 0;
    private TextView Massage_Name,Massage_detail;
    private Button Btn_1hr,Btn_1halfhr,Btn_2hr;
    private String ms_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        changeStatusBarColor();

        Massage_Name = (TextView) findViewById(R.id.massage_name);
        Massage_detail = (TextView) findViewById(R.id.massage_detail);

        Btn_1hr = (Button) findViewById(R.id.btn_1hr);
        Btn_1halfhr = (Button) findViewById(R.id.btn_1halfhr);
        Btn_2hr = (Button) findViewById(R.id.btn_2hr);

        ms_type = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType","Null");

        Log.d("Sus",ms_type);
        if (ms_type.equals("1")){
            Massage_Name.setText("นวดประคบ");
            Massage_detail.setText("บริการนวดประคบ");
        }
        else if (ms_type.equals("2")){
            Massage_Name.setText("นวดกดจุด");
            Massage_detail.setText("บริการนวดกดจุด");
        }
        else if (ms_type.equals("3")){
            Massage_Name.setText("นวดน้ำมัน");
            Massage_detail.setText("บริการนวดน้ำมัน");
        }
        else if (ms_type.equals("4")){
            Massage_Name.setText("นวดฝ่าเท้า");
            Massage_detail.setText("บริการนวดฝ่าเท้า");
        }
        else if (ms_type.equals("5")){
            Massage_Name.setText("นวดไมเกรน");
            Massage_detail.setText("บริการนวดไมเกรน");
        }
        else if (ms_type.equals("6")){
            Massage_Name.setText("นวดสปอร์ต");
            Massage_detail.setText("บริการนวดสปอร์ต");
        }

        Btn_1hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "3600000";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);
            }
        });

        Btn_1halfhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "5400000";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);
            }
        });
        Btn_2hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "7200000";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);
            }
        });

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
