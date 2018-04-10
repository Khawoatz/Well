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
    private TextView Massage_Name,Massage_Peice,tv_price1Hfhr,tv_price2H,tv_priceH1;
    private Button Btn_1hr,Btn_1halfhr,Btn_2hr;
    private String ms_type,ms_typePrict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        changeStatusBarColor();

        Massage_Name = (TextView) findViewById(R.id.massage_name);
        Massage_Peice = (TextView) findViewById(R.id.tv_Price);

        tv_priceH1 = (TextView)findViewById(R.id.tv_priceH1);
        tv_price1Hfhr = (TextView) findViewById(R.id.tv_price1Hfhr);
        tv_price2H = (TextView) findViewById(R.id.tv_price2H);

        Btn_1hr = (Button) findViewById(R.id.btn_1hr);
        Btn_1halfhr = (Button) findViewById(R.id.btn_1halfhr);
        Btn_2hr = (Button) findViewById(R.id.btn_2hr);


        ms_type = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType","Null");
//        ms_typePrict = "000";
        ms_typePrict = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageTypePrice","Null");


        String priceString = ms_typePrict;
        final int priceInt = Integer.parseInt(priceString);

        Log.d("pricccccccc",ms_typePrict);

        Integer timeHr = 1;
        int summ = timeHr*priceInt;
        final String tmpStr10 = String.valueOf(summ);///แปลง Integer เป็น String
        tv_priceH1.setText(tmpStr10);

        Integer timeHr2 = 2;
        int summ2 = timeHr2*priceInt;
        final String tmpStr20 = String.valueOf(summ2);///แปลง Integer เป็น String
        tv_price1Hfhr.setText(tmpStr20);

        Integer timeHr3 = 3;
        int summ3 = timeHr3*priceInt;
        final String tmpStr30 = String.valueOf(summ3);///แปลง Integer เป็น String
        tv_price2H.setText(tmpStr30);

        Log.d("oioioi",tv_priceH1.toString());
  //      tv_priceH1.setText(summ);
//        String tmpStr10 = String.valueOf(summ);///แปลง Integer เป็น String

        Log.d("Sus",ms_type);
        if (ms_type.equals("1")){
            Massage_Name.setText("นวดประคบ");
            Massage_Peice.setText(ms_typePrict);
        }
        else if (ms_type.equals("2")){
            Massage_Name.setText("นวดกดจุด");
            Massage_Peice.setText(ms_typePrict);
        }
        else if (ms_type.equals("3")){
            Massage_Name.setText("นวดน้ำมัน");
            Massage_Peice.setText(ms_typePrict);
        }
        else if (ms_type.equals("4")){
            Massage_Name.setText("นวดฝ่าเท้า");
            Massage_Peice.setText(ms_typePrict);
        }
        else if (ms_type.equals("5")){
            Massage_Name.setText("นวดไมเกรน");
            Massage_Peice.setText(ms_typePrict);
        }
        else if (ms_type.equals("6")){
            Massage_Name.setText("นวดสปอร์ต");
            Massage_Peice.setText(ms_typePrict);
        }

        Btn_1hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "1";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("priceTime",tmpStr10).commit();


            }
        });

        Btn_1halfhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "2";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);

                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("priceTime",tmpStr20).commit();

                //timeservice = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MassageType","Null");

            }
        });
        Btn_2hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeservice = "3";
                Intent i = new Intent(reservation.this,Reservation_schedule.class);
                i.putExtra("Time_Service",timeservice);
                startActivity(i);
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("priceTime",tmpStr30).commit();

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
