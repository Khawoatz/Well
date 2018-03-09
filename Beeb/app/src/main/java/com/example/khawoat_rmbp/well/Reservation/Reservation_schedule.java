package com.example.khawoat_rmbp.well.Reservation;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.Adapter.TimePickerFragment;
import com.example.khawoat_rmbp.well.R;

import java.util.Calendar;

public class Reservation_schedule extends AppCompatActivity {

    TextView tv_Date,tv_Time;
    int d,m,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_schedule);
        findcontrol();
        tv_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                d=calendar.get(Calendar.DAY_OF_MONTH);
                m=calendar.get(Calendar.MONTH);
                y=calendar.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(Reservation_schedule.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        tv_Date.setText("วันที่คุณเลือก : "+i2 +" / "+i1+" / "+i);

                    }
                },y,m,d);
                pickerDialog.show();
            }
        });

        tv_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dFragment = new TimePickerFragment();

                dFragment.show(getFragmentManager(),"Time Picker");
            }
        });

    }
    private void findcontrol(){
        tv_Date = (TextView)findViewById(R.id.tvDate);
        tv_Time = (TextView)findViewById(R.id.tvTime);

    }
}
