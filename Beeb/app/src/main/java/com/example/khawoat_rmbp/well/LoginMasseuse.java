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
import android.widget.TextView;

public class LoginMasseuse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_masseuse);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        TextView tvemail = (TextView) findViewById(R.id.tv_email);
        TextView tvpassword = (TextView) findViewById(R.id.tv_password);
        TextView tvforgot = (TextView) findViewById(R.id.tv_forgotpass);
        TextView tvsignin = (TextView) findViewById(R.id.tv_sign_in);

        EditText etemail = (EditText) findViewById(R.id.et_email);
        EditText etpassword = (EditText) findViewById(R.id.et_password);

        Button btnLogin = (Button) findViewById(R.id.btn_sign_in);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RSUlight.ttf");
        tvemail.setTypeface(custom_font);
        tvpassword.setTypeface(custom_font);
        tvforgot.setTypeface(custom_font);
        tvsignin.setTypeface(custom_font);
        etemail.setTypeface(custom_font);
        etpassword.setTypeface(custom_font);
        btnLogin.setTypeface(custom_font);
        tvforgot.setPaintFlags(tvforgot.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        LinearLayout llbutton = (LinearLayout) findViewById(R.id.ll_button);
        ease(llbutton);

        ImageView ivback = (ImageView) findViewById(R.id.iv_back2);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginMasseuse.this, MainActivity.class));
                finish();
            }
        });
        tvforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMasseuse.this, ForgotPass.class));
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
