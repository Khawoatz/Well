package com.example.khawoat_rmbp.well.Adapter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.khawoat_rmbp.well.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Get a Calendar instance
        final Calendar calendar = Calendar.getInstance();
        // Get the current hour and minute
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        /*
            Creates a new time picker dialog with the specified theme.

                TimePickerDialog(Context context, int themeResId,
                    TimePickerDialog.OnTimeSetListener listener,
                    int hourOfDay, int minute, boolean is24HourView)
         */

        // TimePickerDialog Theme : THEME_DEVICE_DEFAULT_LIGHT
        TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,hour,minute,false);

        // TimePickerDialog Theme : THEME_DEVICE_DEFAULT_DARK
        TimePickerDialog tpd2 = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,hour,minute,false);

        // TimePickerDialog Theme : THEME_HOLO_DARK
        TimePickerDialog tpd3 = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_HOLO_DARK,this,hour,minute,false);

        // TimePickerDialog Theme : THEME_HOLO_LIGHT
        TimePickerDialog tpd4 = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_HOLO_LIGHT,this,hour,minute,false);

        // TimePickerDialog Theme : THEME_TRADITIONAL
        TimePickerDialog tpd5 = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_TRADITIONAL,this,hour,minute,false);

        // Return the TimePickerDialog
        return tpd;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        // Do something with the returned time
        TextView tv = (TextView) getActivity().findViewById(R.id.tvTime);
        tv.setText(hourOfDay + ":" + minute);
    }
}