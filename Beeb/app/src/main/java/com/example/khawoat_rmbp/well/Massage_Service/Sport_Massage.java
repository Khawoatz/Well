package com.example.khawoat_rmbp.well.Massage_Service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.Reservation.reservation;

public class Sport_Massage extends AppCompatActivity {

    private Button btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport__massage);

        btnReserve = (Button) findViewById(R.id.btn_reserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mstype = "6";
                Intent intent = new Intent(Sport_Massage.this,reservation.class);
                PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("นวดสปอร์ต",mstype).commit();
                startActivity(intent);
            }
        });

        changeStatusBarColor();
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
}

