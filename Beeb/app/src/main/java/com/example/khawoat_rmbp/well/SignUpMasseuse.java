package com.example.khawoat_rmbp.well;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpMasseuse extends AppCompatActivity {

    private static final String TAG = "RegisterActivityMasseuse";
    private static final String URL_FOR_REGISTRATION = "http://203.158.131.67/~Adminwell/App/RegisterMass1.php";
    private EditText etname,etsurname,etemail,etpassword,etpasswordrepeat,ettelephone,etage;
    private RadioGroup genderRadioGroup;
    private Button btnSignup;
    private Spinner spinnerProvince;
    ProgressDialog progressDialog;
    private RadioButton rbMale,rbFemale;
    private TextView tvDistrict;
    String [] SpinnerProvince = {"กรุงเทพมหานคร"};
    CheckBox chkOil,chkMigraine,chkAcupunc,chkBall,chkFoot,chkSport;
    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_masseuse);

        /*** ARRAY PROVINCE ***/
        spinnerProvince = (Spinner) findViewById(R.id.dl_province);
        ArrayAdapter arrayProvince = ArrayAdapter.createFromResource(this,R.array.province_array,R.layout.spinner_item);
        spinnerProvince.setAdapter(arrayProvince);

        /*** ARRAY DISTRICT ***/
        listItems = getResources().getStringArray(R.array.district_array);
        tvDistrict = (TextView) findViewById(R.id.tv_district);
        tvDistrict.setHint("กรุณาเลือกเขตของคุณ");
        tvDistrict.setHintTextColor(getResources().getColor(R.color.text_shadow));
        tvDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SignUpMasseuse.this);
                mBuilder.setTitle("กรุณาเลือกเขตของคุณ");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        tvDistrict.setText(listItems[i]);
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


        etname = (EditText) findViewById(R.id.et_name);
        etsurname = (EditText) findViewById(R.id.et_surname);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        etpasswordrepeat = (EditText) findViewById(R.id.et_password_repeat);
        ettelephone = (EditText) findViewById(R.id.et_telephone);
        etage = (EditText) findViewById(R.id.et_age);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        rbMale = (RadioButton) findViewById(R.id.male_radio_btn);
        rbFemale = (RadioButton) findViewById(R.id.female_radio_btn);




//        chkOil = (CheckBox) findViewById(R.id.chk_Oil);
//        chkMigraine = (CheckBox) findViewById(R.id.chk_Migraine);
//        chkAcupunc = (CheckBox) findViewById(R.id.chk_Acupunc);
//        chkBall = (CheckBox) findViewById(R.id.chk_Ball);
//        chkFoot = (CheckBox) findViewById(R.id.chk_Foot);
//        chkSport = (CheckBox) findViewById(R.id.chk_Sport);

        btnSignup = (Button) findViewById(R.id.btn_sign_up);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

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


    private void submitForm() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
//        String textDistrict = spinnerDistrict.getSelectedItem().toString();
        String textProvince = spinnerProvince.getSelectedItem().toString();
        String textDistrict = tvDistrict.getText().toString();
        String textStatus = "Waiting";

        String gender;
        if (selectedId == R.id.female_radio_btn)
            gender = "Female";
        else
            gender = "Male";
        registerUser(etname.getText().toString(),
                etsurname.getText().toString(),
                etemail.getText().toString(),
                etpassword.getText().toString(),
                gender,
                ettelephone.getText().toString(),
                etage.getText().toString(),
                textDistrict,
                textProvince,
                textStatus);
        Log.d("Gender",gender);
        Log.d("District",textDistrict);
        Log.d("Province",textProvince);

    }

    private void registerUser(final String name, final String surname, final String email, final String password, final String gender,
                              final String telephone, final String age, final String address, final String province, final String status) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";
        progressDialog.setMessage("Adding you ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_REGISTRATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response);
                hideDialog();
//                try {
//                    JSONObject jO = new JSONObject(response);
//                    boolean error = jO.getBoolean("error");
                if (response.equals("\r\ntrue")){
//                        String user = jO.getJSONObject("user").getString("Name");
//                        Toast.makeText(getApplicationContext(), "Hi " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();
                    // Launch login activity
                    Intent i = new Intent(SignUpMasseuse.this,LoginMasseuse.class);
                    startActivity(i);
                    Toast.makeText(SignUpMasseuse.this, "Welcome :"+name, Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(SignUpMasseuse.this, "ERROR", Toast.LENGTH_SHORT).show();


                }
//                } catch (JSONException e) {
//                    e.printStackTrace();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Name", name);
                params.put("Surname",surname);
                params.put("Email", email);
                params.put("Password", password);
                params.put("Gender", gender);
                params.put("Telephone",telephone);
                params.put("Age", age);
                params.put("District",address);
                params.put("Province",province);
                params.put("Status_Allow",status);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }
    private void showDialog() {
        if (progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
    public void getChecked(View v){
        String strMessage = "";

        if (chkOil.isChecked()){
            strMessage+="1";
        }
        if (chkMigraine.isChecked()){
            strMessage+="2";
        }
        if (chkAcupunc.isChecked()){
            strMessage+="3";
        }
        if (chkBall.isChecked()){
            strMessage+="4";
        }
        if (chkFoot.isChecked()){
            strMessage+="5";
        }
        if (chkSport.isChecked()){
            strMessage+="6";
        }
    }

}
