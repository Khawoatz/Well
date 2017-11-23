package com.example.khawoat_rmbp.well;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    LinearLayout ll_button;
    private RadioGroup genderRadioGroup;
    private EditText etname,etsurname,etemail,etpassword,etpasswordrepeat,ettelephone,etage;
    private static final String TAG = "RegisterActivity";
    private static final String URL_FOR_REGISTRATION = "https://esaan.000webhostapp.com/test/login_android/register.php";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

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
                Intent i = new Intent(getApplicationContext(),LoginUser.class);
                startActivity(i);
            }
        });

    }

    private void submitForm() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
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
                etage.getText().toString());
    }
    private void registerUser(final String name, final String surname, final String email, final String password, final String telephone,
                              final String gender, final String dob) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";
        progressDialog.setMessage("Adding you ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jO = new JSONObject(response);
                    boolean error = jO.getBoolean("error");
                    if (!error) {
                        String user = jO.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hi " + user +", You are successfully Added!", Toast.LENGTH_SHORT).show();
                        // Launch login activity
                        Intent intent = new Intent(
                                SignUp.this,
                                LoginUser.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jO.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                params.put("name", name);
                params.put("surname",surname);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", gender);
                params.put("age", dob);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
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
