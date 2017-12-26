package com.example.khawoat_rmbp.well;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    LinearLayout ll_button;
    private RadioGroup genderRadioGroup;
    private EditText etname,etsurname,etemail,etpassword,etpasswordrepeat,ettelephone,etage,etarea;
    private static final String TAG = "RegisterActivity";
    private static final String URL_FOR_REGISTRATION = "http://203.158.131.67/~Adminwell/App/register.php";
    ProgressDialog progressDialog;
    Button uploadButton;
    int serverResponseCode = 0;
    String uploadServerUri = null;

    /********* File Path *********/
    final String uploadFilePath = "/mnt/sdcard";
    final String uploadFileName = "id_img.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

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
        TextView tvidimg = (TextView) findViewById(R.id.tv_idimg);
        TextView tvarea = (TextView) findViewById(R.id.tv_area);

        etname = (EditText) findViewById(R.id.et_name);
        etsurname = (EditText) findViewById(R.id.et_surname);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        etpasswordrepeat = (EditText) findViewById(R.id.et_password_repeat);
        ettelephone = (EditText) findViewById(R.id.et_telephone);
        etage = (EditText) findViewById(R.id.et_age);
        etarea = (EditText) findViewById(R.id.et_area);

        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        RadioButton rbMale = (RadioButton) findViewById(R.id.male_radio_btn);
        RadioButton rbFemale = (RadioButton) findViewById(R.id.female_radio_btn);

        Button btnSignup = (Button) findViewById(R.id.btn_sign_up);
        Button btnUpload = (Button) findViewById(R.id.btn_uploadid);

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
        tvidimg.setTypeface(custom_font);
        tvarea.setTypeface(custom_font);
        etarea.setTypeface(custom_font);
        btnUpload.setTypeface(custom_font);
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
                submitForm();
            }
        });

        /********* PHP Script Path *********/
        uploadServerUri = "";
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(SignUp.this,"","Uploading File ...",true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                            }
                        });
                        uploadFile(uploadFilePath + "" + uploadFileName);
                    }
                }).start();
            }
        });

    }

    @SuppressLint("LongLogTag")
    public int uploadFile(String sourceFileUri) {
        String FileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*******";

        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {
            progressDialog.dismiss();

            Log.e("uploadFile","Source File not Exist :"
            +uploadFilePath+""+uploadFileName);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            return 0;
        }
        else
            {

                try {
                    //open a URL connection to the Servlet
                    FileInputStream fileInputStream = new FileInputStream(sourceFile);
                    URL url = new URL(uploadServerUri);

                    //Open a HTTP connection to the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                    conn.setRequestProperty("uploaded_file", fileName);

                    dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
//                    dos.writeBytes("Content-Disposition: form-data; name="uploaded_file";filename=""
//                                    + fileName + """ + lineEnd);

                            dos.writeBytes(lineEnd);

                    // create a buffer of  maximum size
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {

                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    }

                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Responses from the server (code and message)
                    serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                    Log.i("uploadFile", "HTTP Response is : "
                            + serverResponseMessage + ": " + serverResponseCode);

                    if(serverResponseCode == 200){

                        runOnUiThread(new Runnable() {
                            public void run() {

//                                String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
//                                        +" <a class="vglnk" href="http://www.androidexample.com/media/uploads/" rel="nofollow"><span>http</span><span>://</span><span>www</span><span>.</span><span>androidexample</span><span>.</span><span>com</span><span>/</span><span>media</span><span>/</span><span>uploads</span><span>/</span></a>"
//                                +uploadFileName;

//                                messageText.setText(msg);
                                Toast.makeText(SignUp.this, "File Upload Complete.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //close the streams //
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {

                    progressDialog.dismiss();
                    ex.printStackTrace();

                    runOnUiThread(new Runnable() {
                        public void run() {
//                            messageText.setText("MalformedURLException Exception : check script url.");
                            Toast.makeText(SignUp.this, "MalformedURLException",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
                } catch (Exception e) {

                    progressDialog.dismiss();
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        public void run() {
//                            messageText.setText("Got Exception : see logcat ");
                            Toast.makeText(SignUp.this, "Got Exception : see logcat ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("Upload file to server Exception", "Exception : "
                            + e.getMessage(), e);
                }
                progressDialog.dismiss();
                return serverResponseCode;

            } // End else block
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
                etage.getText().toString(),
                etarea.getText().toString());
        Log.d("Gender",gender);

    }
    private void registerUser(final String name, final String surname, final String email, final String password, final String gender,
                              final String telephone, final String age, final String address) {
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
                        String user = jO.getJSONObject("user").getString("Name");
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
//                hideDialog();
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
                params.put("Address",address);
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
