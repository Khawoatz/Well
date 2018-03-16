package com.example.khawoat_rmbp.well.Reservation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.Adapter.TimePickerFragment;
import com.example.khawoat_rmbp.well.R;

import java.util.Calendar;

public class Reservation_schedule extends AppCompatActivity {

    TextView tv_Date, tv_Time;
    private DatePicker datePicker;
    int d, m, y;
    String DateG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_schedule);
        tv_Date = (TextView) findViewById(R.id.tvDate);
        tv_Time = (TextView) findViewById(R.id.tvTime);


        tv_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        tv_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    this, year, month, day);
            calendar.add(Calendar.DATE, 200);

            // Set the Calendar new date as maximum date of date picker
            dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());

            // Subtract 14 days from Calendar updated date
            calendar.add(Calendar.DATE, -200);

            // Set the Calendar new date as minimum date of date picker
            dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());

            // So, now date picker selectable date range is 7 days only

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String monthS;
            String dayS;
            String yearsS;
            yearsS = "" + year;
            if (month < 9) {
                monthS = "0" + (month + 1);
            } else {
                monthS = "" + (month + 1);
            }
            if (day <= 9) {
                dayS = "0" + day;
            } else {
                dayS = "" + day;
            }

            String date;
            date = (yearsS + "-" + monthS + "-" + dayS);
            tv_Date.setText(dayS + "/" + monthS + "/" + yearsS);
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("date", date).commit();
            DateG = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("date", "Null Value");

            Log.d("testDate", date + " " + DateG);
        }
    }
}









//        DialogFragment dFragment = new DatePickerFragment();
//        // Show the date picker dialog fragment
//        dFragment.show(getActivity().getFragmentManager(), "Date Picker");

//        tv_Time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DialogFragment dFragment = new TimePickerFragment();
//
//                dFragment.show(getFragmentManager(),"Time Picker");
//            }
//        });



