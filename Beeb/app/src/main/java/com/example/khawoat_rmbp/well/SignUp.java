package com.example.khawoat_rmbp.well;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {
    LinearLayout ll_button;
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

        EditText etname = (EditText) findViewById(R.id.et_name);
        EditText etsurname = (EditText) findViewById(R.id.et_surname);
        EditText etemail = (EditText) findViewById(R.id.et_email);
        EditText etpassword = (EditText) findViewById(R.id.et_password);
        EditText etpasswordrepeat = (EditText) findViewById(R.id.et_password_repeat);
        EditText ettelephone = (EditText) findViewById(R.id.et_telephone);

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
