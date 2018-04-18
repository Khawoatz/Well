package com.example.khawoat_rmbp.well.Reservation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khawoat_rmbp.well.Adapter.TimeNumberPicker;
import com.example.khawoat_rmbp.well.Adapter.TimePickerFragment;
import com.example.khawoat_rmbp.well.MainActivity;
import com.example.khawoat_rmbp.well.R;
import com.example.khawoat_rmbp.well.SignUpMasseuse;

import java.lang.reflect.Field;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation_schedule extends AppCompatActivity {

    TextView tv_Time;
    TextView tv_Date;
    private DatePicker datePicker;
    long todayCheck;
    final boolean checkFrom = true;
    int arrivalHour, arrivalMinute, arrivalYear, arrivalMonth, arrivalDay;

    Calendar m = Calendar.getInstance();

    // Calender fromCal is for managing "from" time
    Calendar fromCal = Calendar.getInstance();

    final Calendar genCal = Calendar.getInstance();

    // Calender toCal is for managing "To" time
    final Calendar toCal = Calendar.getInstance();

    Button Btn_Next;

    String DateG;
    String time_service;
    Integer time_service_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_schedule);
        tv_Date = (TextView) findViewById(R.id.tvDate);
        tv_Time = (TextView) findViewById(R.id.arrivalTimeText);
        Btn_Next = (Button) findViewById(R.id.btn_next);

        time_service = getIntent().getStringExtra("Time_Service");
        Log.d("Time_Service", "Time_service");

        Calendar c = Calendar.getInstance();
        arrivalDay = c.get(Calendar.DAY_OF_MONTH);
        arrivalMonth = c.get(Calendar.MONTH);
        arrivalYear = c.get(Calendar.YEAR);
        arrivalHour = c.get(Calendar.HOUR_OF_DAY);
        arrivalMinute = c.get(Calendar.MINUTE);

//        time_service = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("timeservice123","null");
        time_service_int = Integer.parseInt(time_service);

        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
        String cTime = format.format(c.getTime());
        fromCal.setTimeInMillis(c.getTimeInMillis());

        c.setTimeInMillis(c.getTimeInMillis() + time_service_int);
        toCal.setTimeInMillis(c.getTimeInMillis());
        m.setTimeInMillis(c.getTimeInMillis());
        String fTime = format.format(c.getTime());

//        tv_Time.setText(cTime + " - " + fTime);
        tv_Time.setText("กรุณาเลือกเวลาเริ่มให้บริการ");

        tv_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        Btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Reservation_schedule.this, Reservation_location.class);
                startActivity(i);


            }
        });

        RelativeLayout arrivalTimeLayout = (RelativeLayout) findViewById(R.id.arrivalTime);
        tv_Time = (TextView) findViewById(R.id.arrivalTimeText);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 120);
        tv_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialog();
                // Initialize a new time picker dialog fragment
//                DialogFragment dFragment = new TimePickerFragment();
//
//                // Show the time picker dialog fragment
//                dFragment.show(getFragmentManager(), "Time Picker");
            }
        });

    }

    private void timeDialog() {

        final String[] hour = new String[]{"9", "10", "11", "12", "13", "14", "15", "16", "17","18"};
        final Integer[] hourint = new Integer[]{9, 10, 11, 12, 13, 14, 15, 16, 17,18};
        final String[] hour2 = new String[]{"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
        final String[] hour3 = new String[]{"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"};
        final String[] minute = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Reservation_schedule.this);
        mBuilder.setTitle("กรุณาเลือกเวลาเริ่มให้บริการ");
        mBuilder.setSingleChoiceItems(hour, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
//                Integer toTimeint = Integer.parseInt(hour[i]);

                Integer hourcheckfrom = Integer.parseInt(hour[i])+time_service_int;
                Integer hourcheckto = Integer.parseInt(hour[9]);
                if (hourcheckfrom > hourcheckto){
//                    Toast.makeText(Reservation_schedule.this, "This is my Toast message!",
//                            Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Reservation_schedule.this);
                    builder1.setMessage("กรุณาเลือกเวลาใหม่อีกครั้ง");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } else {
                    String fromTime = hour[i]+":00";
                    String toTime = hourcheckfrom.toString()+":00";
                    tv_Time.setText(fromTime+" - "+toTime);
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("StartTime", fromTime).commit();
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("EndTime", toTime).commit();

                    Log.d("starttime",fromTime.toString());
                    Log.d("starttime",toTime.toString());
                }

//                Integer toTimeplus = toTimeint + 2;
//                String toTime = toTimeplus.toString();
//                String toTime = hour[i+time_service_int];
//                tv_Time.setText(fromTime+" - "+toTime);

                dialog.dismiss();
            }
        });
        android.app.AlertDialog mDialog = mBuilder.create();
        mDialog.show();
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
//            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("dateService",date).commit();
            DateG = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("date", "Null Value");

            //    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("date",date).commit();
            Log.d("testDate", date + " " + DateG);
        }
    }
}


//    public static boolean setNumberPickerTextColor(TimeNumberPicker numberPicker)
//    {
//        final int count = numberPicker.getChildCount();
//        for(int i = 0; i < count; i++){
//            View child = numberPicker.getChildAt(i);
//            if(child instanceof EditText){
//                try{
//                    Field selectorWheelPaintField = numberPicker.getClass()
//                            .getDeclaredField("mSelectorWheelPaint");
//                    selectorWheelPaintField.setAccessible(true);
//                    ((EditText)child).setTextSize(30);
//                    numberPicker.invalidate();
//                    return true;
//                }
//                catch(NoSuchFieldException e){
//                    Log.w("setNumberPicker", e);
//                }
//                catch(IllegalArgumentException e){
//                    Log.w("setNumberPicker", e);
//                }
//            }
//        }
//        return false;
//    }

//        private static String pad(int c) {
//            if (c >= 10)
//                return String.valueOf(c);
//            else
//                return "0" + String.valueOf(c);
//        }
//
//
//        public static String month(String months) {
//            switch (months) {
//                case "1":
//                    return "Jan";
//                case "2":
//                    return "Feb";
//                case "3":
//                    return "Mar";
//                case "4":
//                    return "Apr";
//                case "5":
//                    return "May";
//                case "6":
//                    return "Jun";
//                case "7":
//                    return "Jul";
//                case "8":
//                    return "Aug";
//                case "9":
//                    return "Sept";
//                case "10":
//                    return "Oct";
//                case "11":
//                    return "Nov";
//                case "12":
//                    return "Dec";
//                default:
//                    return null;
//            }
//
//        }















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



