package com.example.khawoat_rmbp.well;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SignUpMasseuse extends AppCompatActivity {

    private static final String TAG = "RegisterActivityMasseuse";
    private static final String URL_FOR_REGISTRATION = "http://203.158.131.67/~Adminwell/App/register_masseuse.php";
    ProgressDialog progressDialog;
    String [] SpinnerProvince = {"กรุงเทพมหานคร"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_masseuse);

        Spinner spinnerProvince = (Spinner) findViewById(R.id.dl_province);
//        ArrayAdapter<String> arrayProvince=new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line,SpinnerProvince);
        ArrayAdapter arrayProvince = ArrayAdapter.createFromResource(this,R.array.province_array,R.layout.spinner_item);
        spinnerProvince.setAdapter(arrayProvince);

        Spinner spinnerDistrict = (Spinner) findViewById(R.id.dl_district);
//        ArrayAdapter<String> arrayDistrict = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.district_array));
        ArrayAdapter arrayDistrict = ArrayAdapter.createFromResource(this,R.array.district_array,R.layout.spinner_item);
        spinnerDistrict.setAdapter(arrayDistrict);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();
    }
    /*** Making notification bar transparent ***/
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
