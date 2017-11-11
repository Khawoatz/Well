package com.example.khawoat_rmbp.well;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import org.w3c.dom.Text;

public class ForgotPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        TextView tvforgotpass = (TextView) findViewById(R.id.tv_forgot_pass);
        TextView tvemail = (TextView) findViewById(R.id.tv_email);
        TextView tvdetailforgot = (TextView) findViewById(R.id.tv_detail_forgot);
        EditText etemail = (EditText) findViewById(R.id.et_email);
        Button btnSend = (Button) findViewById(R.id.btn_forgot_pass);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/RSUlight.ttf");
        tvforgotpass.setTypeface(custom_font);
        tvemail.setTypeface(custom_font);
        tvdetailforgot.setTypeface(custom_font);
        etemail.setTypeface(custom_font);
        btnSend.setTypeface(custom_font);

        ImageView ivback = (ImageView) findViewById(R.id.iv_back2);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPass.this, LoginUser.class));
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
}
