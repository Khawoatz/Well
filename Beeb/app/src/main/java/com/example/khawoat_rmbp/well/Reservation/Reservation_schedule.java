package com.example.khawoat_rmbp.well.Reservation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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

import com.example.khawoat_rmbp.well.Adapter.TimeNumberPicker;
import com.example.khawoat_rmbp.well.Adapter.TimePickerFragment;
import com.example.khawoat_rmbp.well.R;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation_schedule extends AppCompatActivity {

    TextView tv_Date, tv_Time;
    private DatePicker datePicker;
    long todayCheck;
    final boolean checkFrom = true;
    int arrivalHour, arrivalMinute, arrivalYear,arrivalMonth,arrivalDay;

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
        Log.d("Time_Service","Time_service");

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

        c.setTimeInMillis(c.getTimeInMillis()+time_service_int);
        toCal.setTimeInMillis(c.getTimeInMillis());
        m.setTimeInMillis(c.getTimeInMillis());
        String fTime = format.format(c.getTime());

        tv_Time.setText(cTime+" - "+fTime);

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



            }
        });

        RelativeLayout arrivalTimeLayout = (RelativeLayout) findViewById(R.id.arrivalTime);
        tv_Time = (TextView)findViewById(R.id.arrivalTimeText);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,120);
        tv_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialog();
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
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("dateService",date).commit();
            DateG = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("date", "Null Value");

            Log.d("testDate", date + " " + DateG);
        }


    }



        private static String pad(int c) {
            if (c >= 10)
                return String.valueOf(c);
            else
                return "0" + String.valueOf(c);
        }

        public void timeDialog(){

            AlertDialog.Builder timedialog = new AlertDialog.Builder(Reservation_schedule.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogViewTime = inflater.inflate(R.layout.dialog_timepicker, null);
            timedialog.setView(dialogViewTime);

            final TimeNumberPicker p1 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p1);
            final TimeNumberPicker p2 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p2);
            final TimeNumberPicker p3 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p3);
            final TimeNumberPicker p4 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p4);
            final TimeNumberPicker p5 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p5);
            final TimeNumberPicker p6 = (TimeNumberPicker) dialogViewTime.findViewById(R.id.p6);
            setNumberPickerTextColor(p1);
            setNumberPickerTextColor(p2);
            setNumberPickerTextColor(p3);
            setNumberPickerTextColor(p4);
            setNumberPickerTextColor(p5);
            setNumberPickerTextColor(p6);
            final TextView fromTime = (TextView) dialogViewTime.findViewById(R.id.fromTime);
            final TextView toTime = (TextView) dialogViewTime.findViewById(R.id.toTime);
            final Button ok = (Button) dialogViewTime.findViewById(R.id.confirmOk);
            final Button cancel = (Button) dialogViewTime.findViewById(R.id.confirmNotOk);

            final String[] hour = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            final String[] minute = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35","36", "37", "38", "39", "40", "41", "42", "43","44","45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55","56", "57", "58", "59"};
//                final String[] minute = new String[]{"00", "15", "30", "45"};
            final String[] aa = new String[]{"AM", "PM"};


            //set p1 values for "from" hour
            p1.setMaxValue(hour.length-1);
            p1.setDisplayedValues(hour);
            p1.setValue(oneToTwelve(fromCal.get(Calendar.HOUR_OF_DAY)) - 1);
            p1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            //detects the change for p1 values
            p1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    p1.setValue(i1);
                    fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));

                    Log.e("fromCal.set",""+fromCal.getTimeInMillis());
                    Log.e("genCal.set",""+genCal.getTimeInMillis());

                    //Set p3 value to AM or PM depending on Calender "fromCal" time
                    if(AMorPM(fromCal.get(Calendar.HOUR_OF_DAY)) == 1){
                        p3.setValue(1);
                    } else {
                        p3.setValue(0);
                    }

                    //To check the change from 11:59 AM to 12:00 PM
                    if((i==10 && i1==11) && (p3.getValue() == 0)){
                        p3.setValue(1);
                    }

                    fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));

                    //Can't select past time
                    if(fromCal.getTimeInMillis() < genCal.getTimeInMillis()){
                        p1.setValue(i);
                        fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));
                    }


                    toCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue())+2,Integer.parseInt(minute[p2.getValue()]));
                    Log.e("toCal.set",""+toCal.getTimeInMillis());
                    p4.setValue(oneToTwelve(toCal.get(Calendar.HOUR_OF_DAY)) - 1);


                    //Set p6 value to AM or PM depending on Calender "toCal" time
                    if(AMorPM(toCal.get(Calendar.HOUR_OF_DAY)) == 1){
                        p6.setValue(1);
                    } else {
                        p6.setValue(0);
                    }

                    //set the text according to the values in the number pickers
                    fromTime.setText(hour[p1.getValue()]+":"+minute[p2.getValue()]+" "+aa[p3.getValue()]);
                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
//                setNumberPickerTextColor(p1,Color.BLACK);
                }
            });

            //set p2 values for "from" minute

            p2.setMaxValue(minute.length-1);
            p2.setDisplayedValues(minute);
            p2.setValue(fromCal.get(Calendar.MINUTE));
            p2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            //detects the change for p2 values
            p2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    p2.setValue(i1);
                    fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));

                    //Can't select past time based on minute
                    if(fromCal.getTimeInMillis() < genCal.getTimeInMillis()){
                        p2.setValue(i);
                        fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));
                    }
                    p5.setValue(p2.getValue());

                    fromTime.setText(hour[p1.getValue()]+":"+minute[p2.getValue()]+" "+aa[p3.getValue()]);
                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
                }
            });

            //set p3 values for "from" AM or PM
            p3.setMinValue(0);
            p3.setMaxValue(aa.length-1);
            p3.setDisplayedValues(aa);
            if(AMorPM(fromCal.get(Calendar.HOUR_OF_DAY)) == 1){
                p3.setValue(1);
            } else {
                p3.setValue(0);
            }
            p3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            //detects the change for p3 values
            p3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    p3.setValue(i1);
                    fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));

                    Log.e("fromCal.set",""+fromCal.getTimeInMillis());
                    Log.e("onetoTwenty",""+oneToTwentyFour(hour[p1.getValue()],p3.getValue()));

                    //can't select the past time
                    if(fromCal.getTimeInMillis() < genCal.getTimeInMillis()){
                        p3.setValue(i);
                        fromCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue()),Integer.parseInt(minute[p2.getValue()]));
                    }

                    toCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue())+2,Integer.parseInt(minute[p2.getValue()]));
                    p4.setValue(oneToTwelve(toCal.get(Calendar.HOUR_OF_DAY)) - 1);

                    //Set p6 value to AM or PM depending on Calender "toCal" time
                    if(AMorPM(toCal.get(Calendar.HOUR_OF_DAY)) == 1){
                        p6.setValue(1);
                    } else {
                        p6.setValue(0);
                    }

                    fromTime.setText(hour[p1.getValue()]+":"+minute[p2.getValue()]+" "+aa[p3.getValue()]);
                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
                }
            });

            toCal.set(fromCal.get(Calendar.YEAR),fromCal.get(Calendar.MONTH),fromCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p1.getValue()],p3.getValue())+2,Integer.parseInt(minute[p2.getValue()]));
            fromTime.setText(hour[p1.getValue()]+":"+minute[p2.getValue()]+" "+aa[p3.getValue()]);

            p4.setMinValue(0);
            p4.setMaxValue(hour.length-1);
            p4.setDisplayedValues(hour);
            p4.setValue(oneToTwelve(toCal.get(Calendar.HOUR_OF_DAY)) - 1);
            p4.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            p4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    p4.setValue(i1);

                    toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    Log.e("toCal.setp4",""+toCal.getTimeInMillis());

                    if(toCal.getTimeInMillis() < fromCal.getTimeInMillis()){
                        p4.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    //checking if time selected is more than 8 hours
                    if(fromCal.getTimeInMillis() + time_service_int < toCal.getTimeInMillis()){
                        p4.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    //Set p6 value to AM or PM depending on Calender "toCal" time
                    if(AMorPM(toCal.get(Calendar.HOUR_OF_DAY)) == 1){
                        p6.setValue(1);
                    } else {
                        p6.setValue(0);
                    }

                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
                }
            });

            p5.setMinValue(0);
            p5.setMaxValue(minute.length-1);
            p5.setDisplayedValues(minute);
            p5.setValue(p2.getValue());
            p5.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

            p5.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    p5.setValue(i1);

                    toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));

                    if(toCal.getTimeInMillis() < fromCal.getTimeInMillis()){
                        p5.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    //checking if time selected is more than 8 hours
                    if(fromCal.getTimeInMillis() + time_service_int < toCal.getTimeInMillis()){
                        p5.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
                }
            });

            p6.setMinValue(0);
            p6.setMaxValue(aa.length-1);
            p6.setDisplayedValues(aa);
            if(AMorPM(toCal.get(Calendar.HOUR_OF_DAY)) == 1){
                p6.setValue(1);
            } else {
                p6.setValue(0);
            }
            p6.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);

            p6.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    p6.setValue(i1);
                    toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));

                    if(toCal.getTimeInMillis() < fromCal.getTimeInMillis()){
                        p6.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    //checking if time selected is more than กี่ชม.ที่ส่งค่ามา hours
//                    if(fromCal.getTimeInMillis()+7200000 < toCal.getTimeInMillis()){
                        if(fromCal.getTimeInMillis()+time_service_int < toCal.getTimeInMillis()){
                        p6.setValue(i);
                        toCal.set(toCal.get(Calendar.YEAR),toCal.get(Calendar.MONTH),toCal.get(Calendar.DAY_OF_MONTH),oneToTwentyFour(hour[p4.getValue()],p6.getValue()),Integer.parseInt(minute[p5.getValue()]));
                    }

                    toTime.setText(hour[p4.getValue()]+":"+minute[p5.getValue()]+" "+aa[p6.getValue()]);
                }
            });

            final Dialog d = timedialog.create();
            d.show();

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
                    String fTime = format.format(fromCal.getTime());
                    String tTime = format.format(toCal.getTime());
                    tv_Time.setText(fTime+" - "+tTime);
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("dateStart",fTime).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("dateEnd",tTime).commit();
                    d.dismiss();

                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
        }

        //returns in 12 hour format given 24 hour format as argument
        public int oneToTwelve(int hour){
            if(hour>12 && hour<=23){
                return hour-12;
            }
            return hour;
        }

        //returns if time is in AM or PM
        public int AMorPM(int hour){
            if(hour>=12 && hour<=23){
                return 1;
            }
            return 0;
        }

        //returns in 24 hour format when given in 12 hour format along with AM or PM as arguments
        public int oneToTwentyFour(String hour, int k){
            if((k == 0) && Integer.parseInt(hour) == 12){
                return 0;
            }
            if(k == 1 && Integer.parseInt(hour) <= 11){
                return Integer.parseInt(hour)+12;
            } else {
                return Integer.parseInt(hour);
            }
        }

        public static boolean setNumberPickerTextColor(TimeNumberPicker numberPicker)
        {
            final int count = numberPicker.getChildCount();
            for(int i = 0; i < count; i++){
                View child = numberPicker.getChildAt(i);
                if(child instanceof EditText){
                    try{
                        Field selectorWheelPaintField = numberPicker.getClass()
                                .getDeclaredField("mSelectorWheelPaint");
                        selectorWheelPaintField.setAccessible(true);
                        ((EditText)child).setTextSize(30);
                        numberPicker.invalidate();
                        return true;
                    }
                    catch(NoSuchFieldException e){
                        Log.w("setNumberPicker", e);
                    }
                    catch(IllegalArgumentException e){
                        Log.w("setNumberPicker", e);
                    }
                }
            }
            return false;
        }

        public static String month(String months)
        {
            switch (months)
            {
                case "1":
                    return "Jan";
                case "2":
                    return "Feb";
                case "3":
                    return "Mar";
                case "4":
                    return "Apr";
                case "5":
                    return "May";
                case "6":
                    return "Jun";
                case "7":
                    return "Jul";
                case "8":
                    return "Aug";
                case "9":
                    return "Sept";
                case "10":
                    return "Oct";
                case "11":
                    return "Nov";
                case "12":
                    return "Dec";
                default:
                    return null;
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



