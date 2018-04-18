package com.example.khawoat_rmbp.well;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.Adapter.RequestHandler;
import com.example.khawoat_rmbp.well.Upload_Photo.Photo_User_Citizen;
import com.example.khawoat_rmbp.well.Upload_Photo.Photo_MassageCertificate_Mass;
import com.example.khawoat_rmbp.well.Upload_Photo.Photo_User_Citizen;
import com.example.khawoat_rmbp.well.Upload_Photo.Photo_citizenpic_mass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    LinearLayout ll_button;
    private RadioGroup genderRadioGroup;
    private EditText etname,etsurname,etemail,etpassword,etpasswordrepeat,ettelephone,etage,etarea;
    private static final String TAG = "RegisterActivity";
    private static final String URL_FOR_REGISTRATION = "http://203.158.131.67/~Adminwell/App/register_user.php";
    private static final String UPLOAD_URL = "http://203.158.131.67/~Adminwell/App/upload.php";
    private static final String UPLOAD_KEY = "image";
    private Spinner spinnerDistrict,spinnerProvince;
    ProgressDialog progressDialog;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    private Bitmap bitmap;
    private ImageView img_ID;
    private Button btnUpload;
    private String result;
    private String uploadImage;
    TextView tvDistrict;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvDistrict = (TextView) findViewById(R.id.tv_district);
        tvDistrict.setHint("กรุณาเลือกเขตของคุณ");
        tvDistrict.setHintTextColor(getResources().getColor(R.color.text_shadow));

        /*** ARRAY DISTRICT ***/


//        final ArrayList itemSelected = new ArrayList();
        tvDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
                        tvDistrict.setText("");
                        for (int i = 0; i<checkedItem.length;i++){
                            boolean checked = checkedItem[i];
                            if (checked){
                                tvDistrict.setText(tvDistrict.getText()+districtList.get(i)+",");
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







        spinnerProvince = (Spinner) findViewById(R.id.dl_province);
////        ArrayAdapter<String> arrayProvince=new ArrayAdapter<String>(this,
////                android.R.layout.simple_dropdown_item_1line,SpinnerProvince);
        ArrayAdapter arrayProvince = ArrayAdapter.createFromResource(this,R.array.province_array,R.layout.spinner_item);
        spinnerProvince.setAdapter(arrayProvince);

//        spinnerDistrict = (Spinner) findViewById(R.id.dl_district);
////        ArrayAdapter<String> arrayDistrict = new ArrayAdapter<String>(this,
////                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.district_array));
//        ArrayAdapter arrayDistrict = ArrayAdapter.createFromResource(this,R.array.district_array,R.layout.spinner_item);
//        spinnerDistrict.setAdapter(arrayDistrict);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

      //  img_ID = (ImageView) findViewById(R.id.imgID);

        TextView tvsignup = (TextView) findViewById(R.id.tv_sign_up);
        TextView tvhaveacc = (TextView) findViewById(R.id.tv_have_acc);
        TextView tvname = (TextView) findViewById(R.id.tv_name);
        TextView tvsurname = (TextView) findViewById(R.id.tv_surname);
        TextView tvemail = (TextView) findViewById(R.id.tv_email);
        TextView tvpassword = (TextView) findViewById(R.id.tv_password);
        TextView tvsignin = (TextView) findViewById(R.id.tv_sign_in);
        TextView tvpasswordrepeat = (TextView) findViewById(R.id.tv_password_repeat);
        TextView tvgender = (TextView) findViewById(R.id.tv_gender);
        TextView tvtelephone = (TextView) findViewById(R.id.tv_telephone);
        TextView tvage = (TextView) findViewById(R.id.tv_age);
     //   TextView tvidimg = (TextView) findViewById(R.id.tv_idimg);
        TextView tvarea = (TextView) findViewById(R.id.tv_area);

        etname = (EditText) findViewById(R.id.et_name);
        etsurname = (EditText) findViewById(R.id.et_surname);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        etpasswordrepeat = (EditText) findViewById(R.id.et_password_repeat);
        ettelephone = (EditText) findViewById(R.id.et_telephone);
        etage = (EditText) findViewById(R.id.et_age);
        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        RadioButton rbMale = (RadioButton) findViewById(R.id.male_radio_btn);
        RadioButton rbFemale = (RadioButton) findViewById(R.id.female_radio_btn);

        Button btnSignup = (Button) findViewById(R.id.btn_sign_up);
      //  btnUpload = (Button) findViewById(R.id.btn_uploadid);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RSUlight.ttf");
        tvhaveacc.setTypeface(custom_font);
        tvsignup.setTypeface(custom_font);
        tvname.setTypeface(custom_font);
        tvsurname.setTypeface(custom_font);
        tvemail.setTypeface(custom_font);
        tvpassword.setTypeface(custom_font);
        tvsignin.setTypeface(custom_font);
        tvgender.setTypeface(custom_font);
        etname.setTypeface(custom_font);
        etsurname.setTypeface(custom_font);
        etemail.setTypeface(custom_font);
        etpassword.setTypeface(custom_font);
        btnSignup.setTypeface(custom_font);
        tvpasswordrepeat.setTypeface(custom_font);
        etpasswordrepeat.setTypeface(custom_font);
        tvtelephone.setTypeface(custom_font);
        ettelephone.setTypeface(custom_font);
        tvage.setTypeface(custom_font);
        etage.setTypeface(custom_font);
        rbMale.setTypeface(custom_font);
        rbFemale.setTypeface(custom_font);
     //   tvidimg.setTypeface(custom_font);
        tvarea.setTypeface(custom_font);
//        etarea.setTypeface(custom_font);
//        btnUpload.setTypeface(custom_font);
        ll_button = (LinearLayout) findViewById(R.id.ll_button);
        ease(ll_button);

        tvsignin.setPaintFlags(tvsignin.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        ImageView ivback = (ImageView) findViewById(R.id.iv_back2);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
                finish();
            }
        });


        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,LoginUser.class));
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                uploadImage();
               submitForm();

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img_ID.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }





    private void submitForm() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
//        String textDistrict = spinnerDistrict.getSelectedItem().toString();
        String textProvince = spinnerProvince.getSelectedItem().toString();
        String textDistrict = tvDistrict.getText().toString();

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
                textProvince);
//                citizenid);
        Log.d("Gender",gender);
        Log.d("District",textDistrict);
        Log.d("Province",textProvince);

    }
    private void registerUser(final String name, final String surname, final String email, final String password, final String gender,
                              final String telephone, final String age, final String address, final String province) {
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
                    if (response.equals("true")){
//                        String user = jO.getJSONObject("user").getString("Name");
//                        Toast.makeText(getApplicationContext(), "Hi " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();
                        // Launch login activity
                        Intent i = new Intent(SignUp.this,Photo_User_Citizen.class);
                        startActivity(i);
                    Toast.makeText(SignUp.this, "Welcome :"+name, Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(SignUp.this, "ERROR", Toast.LENGTH_SHORT).show();


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
              //  params.put("Citizen_Pic",citizenpic);
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

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void ease(final View view) {
        Easing easing = new Easing(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        float fromY = 600;
        float toY = view.getTop();
        ValueAnimator valueAnimatorY = ValueAnimator.ofFloat(fromY,toY);

        valueAnimatorY.setEvaluator(easing);

        valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float) animation.getAnimatedValue());
            }
        });

        animatorSet.playTogether(valueAnimatorY);
        animatorSet.setDuration(700);
        animatorSet.start();
    }


}
