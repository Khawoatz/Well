package com.example.khawoat_rmbp.well;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.Fragment_Masseuse.MainFragmentMasseuse;

import java.util.Arrays;
import java.util.List;

public class Mass_Choose_Work extends AppCompatActivity {
    AlertDialog mDialog = null;
    /**
     * This becomes false when "Select All" is selected while deselecting some other item on list.
     */
    boolean selectAll = true;
    /**
     * Number of items in array list and eventually in ListView
     */
    int length;
    CheckBox herbal,acu,oil,foot,mig,sport;
    TextView tv_district_mass;
    Dialog dialog;
    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass__choose__work);

        herbal = (CheckBox) findViewById(R.id.cb_Herbal);
        acu = (CheckBox) findViewById(R.id.cb_Acu);
        oil = (CheckBox) findViewById(R.id.cb_Oil);
        foot = (CheckBox) findViewById(R.id.cb_Foot);
        mig = (CheckBox) findViewById(R.id.cb_Mig);
        sport = (CheckBox) findViewById(R.id.cb_Sport);

        btn_next = (Button) findViewById(R.id.btn_next_Main);

        tv_district_mass = (TextView) findViewById(R.id.tv_District_Mass);
        tv_district_mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mass_Choose_Work.this);
                final String[] Dialog_district = getResources().getStringArray(R.array.district_array);
                final boolean[] checkedItem = new boolean[Dialog_district.length];
                final List<String> districtList = Arrays.asList(Dialog_district);

                builder.setMultiChoiceItems(Dialog_district, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        checkedItem[which] = isChecked;

                        String currentItem = districtList.get(which);

                    }
                });

                builder.setCancelable(false);
                builder.setTitle(" กรุณาเลือกเขตของคุณ ");

                builder.setNegativeButton("เสร็จสิ้น", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_district_mass.setText("");
                        for (int i = 0; i<checkedItem.length;i++){
                            boolean checked = checkedItem[i];
                            if (checked){
                                tv_district_mass.setText(tv_district_mass.getText()+districtList.get(i)+",");
                                String districtmass = tv_district_mass.toString();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("districtmass",districtmass).commit();
                            }
                        }

                    }
                });
                builder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

        if (herbal.isChecked()){
            String herbalchoose = "1";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("herbaltype",herbalchoose).commit();

        }
        if (acu.isChecked()){
            String acuchoose = "2";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("acutype",acuchoose).commit();

        }
        if (oil.isChecked()){
            String oilchoose = "3";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("oiltype",oilchoose).commit();

        }
        if (foot.isChecked()){
            String footchoose = "4";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("foottype",footchoose).commit();

        }
        if (mig.isChecked()){
            String migchoose = "5";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("migtype",migchoose).commit();

        }
        if (sport.isChecked()){
            String sportchoose = "6";
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("sporttype",sportchoose).commit();

        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mass_Choose_Work.this, MainFragmentMasseuse.class);
                startActivity(i);
            }
        });
//        length = getResources().getStringArray(R.array.district_array_mass).length;
    }
//    public void onDialog (View v) {
//    }

//        mDialog = onCreateDialog(null);
//        mDialog.show();
//        // we get the ListView from already shown dialog
//        final ListView listView = mDialog.getListView();
//        // ListView Item Click Listener that enables "Select all" choice
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                boolean isChecked = listView.isItemChecked(position);
//                if (position == 0) {
//                    if(selectAll) {
//                        for (int i = 1; i < length; i++) { // we start with first element after "Select all" choice
//                            if (isChecked && !listView.isItemChecked(i)
//                                    || !isChecked && listView.isItemChecked(i)) {
//                                listView.performItemClick(listView, i, 0);
//                            }
//                        }
//                    }
//                } else {
//                    if (!isChecked && listView.isItemChecked(0)) {
//                        // if other item is unselected while "Select all" is selected, unselect "Select all"
//                        // false, performItemClick, true is a must in order for this code to work
//                        selectAll = false;
//                        listView.performItemClick(listView, 0, 0);
//                        selectAll = true;
//                    }
//                }
//            }
//        });
//    }

//    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(Mass_Choose_Work.this);
//        // Set the dialog title
//        builder.setTitle("กรุณาเลือกเขตพื้นที่การทำงานของคุณ")
//                // Specify the list array, the items to be selected by default (null for none),
//                // and the listener is null in order to "Select all" choice to work
//                .setMultiChoiceItems(R.array.district_array_mass, null, null)
//                // Set the action buttons
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        tv_district_mass.setText(tv_district_mass.getText()+);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {}
//                });
//        return builder.create();
//    }
}
