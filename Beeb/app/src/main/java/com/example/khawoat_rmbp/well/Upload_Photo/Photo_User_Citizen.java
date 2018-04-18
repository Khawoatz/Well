package com.example.khawoat_rmbp.well.Upload_Photo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.khawoat_rmbp.well.AppSingleton;
import com.example.khawoat_rmbp.well.Constants;
import com.example.khawoat_rmbp.well.LoginUser;
import com.example.khawoat_rmbp.well.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by jah on 4/16/2018.
 */

public class Photo_User_Citizen extends AppCompatActivity implements View.OnClickListener{
    private static final String URL_FOR_UPDATE_SELECT_MAX_IDCUS = "http://203.158.131.67/~Adminwell/App/Select_maxid_citizen_user.php";
    private Button buttonChoose,buttonnext;
    private Button buttonUpload;
    private ImageView imageView;
    private TextView tv_name;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    private String IDMaxMass;
    private String cusidmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_citizen_user);

        //Requesting storage permission
        requestStoragePermission();

        //Initializing views
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imageView = (ImageView) findViewById(R.id.imageView);
        tv_name = (TextView) findViewById(R.id.tv_name);
        buttonnext = (Button) findViewById(R.id.btn_next);

        //Setting clicklistener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        showmassid();
    }

    private void showmassid() {
        String cancel_req_tag = "select";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_UPDATE_SELECT_MAX_IDCUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.d("responseIDMASS",response.toString());
                    cusidmax = jObj.getString("id");

                    //                 Log.d("IDMASS",IDMASS);


                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("IDmassMax", cusidmax).commit();
//                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("sAddress", Address).commit();
//                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("sLatitude", Latitude).commit();

                    //  Toast.makeText(Photo_citizenpic_mass.this,"dddddd", Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                    //      Toast.makeText(Photo_citizenpic_mass.this,"FFFF", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppSingleton.getInstance(this).addToRequestQueue(strReq, cancel_req_tag);
    }
    public void uploadMultipart(){
        //getting name for the image
        //  String name = tv_name.getText().toString().trim();
        String name = cusidmax;
        Log.d("IDansa",cusidmax.toString());
        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL_CUSCITIZEN)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(Photo_User_Citizen.this,android. Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android. Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage ---- "+grantResults, Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadMultipart();
            Toast.makeText(Photo_User_Citizen.this, "Upload Success ", Toast.LENGTH_SHORT).show();

        }
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Photo_User_Citizen.this,LoginUser.class);
                startActivity(i);
            }
        });
    }
}
