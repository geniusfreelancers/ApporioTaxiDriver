package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.models.register.Register;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.others.ImageCompressMode;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import com.apporio.demotaxiappdriver.logger.Logger;

public class ProfileActivity extends AppCompatActivity implements ApiFetcher, Apis {
    TextView email;
    ImageView iv_edit_name, iv_edit_phone;
    LinearLayout ll_back_profile, ll_done_profile, ll_change_password, ll_logout;
    EditText name, mobile;
    String driverid, drivername, drivermobile, drivermail, driverimage;
    public static Activity profileActivity;

    ImageView iv_profile_pic_upload;

    SessionManager sessionManager;
    Uri selectedImage;
    Bitmap bitmap1;
    String imagePath = "", imagePathCompressed = "";

    ProgressDialog pd;

    Dialog dialog;

    AccountModule accountModule;

    LanguageManager languageManager;
    String language_id;

    String driver_token;

    FirebaseUtils firebasutil ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebasutil = new FirebaseUtils(this);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        profileActivity = this;

        pd = new ProgressDialog(this);
        pd.setMessage(ProfileActivity.this.getResources().getString(R.string.loading));

        accountModule = new AccountModule(ProfileActivity.this);

        ll_back_profile = (LinearLayout) findViewById(R.id.ll_back_profile);
        ll_done_profile = (LinearLayout) findViewById(R.id.ll_done_profile);
        ll_change_password = (LinearLayout) findViewById(R.id.ll_change_password);
        ll_logout = (LinearLayout) findViewById(R.id.ll_logout);
        iv_profile_pic_upload = (ImageView) findViewById(R.id.iv_profile_pic_upload);

        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mob);
        name.setEnabled(false);
        mobile.setEnabled(false);

        email = (TextView) findViewById(R.id.email);
        iv_edit_name = (ImageView) findViewById(R.id.iv_edit_name);
        iv_edit_phone = (ImageView) findViewById(R.id.iv_edit_phone);

        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        driverid = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        drivername = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        drivermobile = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE);
        drivermail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverimage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);

        name.setText(drivername);
        mobile.setText(drivermobile);
        email.setText(drivermail);
        Picasso.with(ProfileActivity.this)
                .load(Config.app_config.getResponse().getDriver_config().getBase_url() + driverimage)
                .placeholder(R.drawable.dummy_pic)
                .error(R.drawable.dummy_pic)
                .fit()
                .into(iv_profile_pic_upload);

        iv_profile_pic_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i1.setType("image/*");
                startActivityForResult(i1, 101);
            }
        });

        ll_back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ChangePasswordActivity.class));
            }
        });

        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlogoutdialog();
            }
        });

        iv_edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                name.setClickable(true);
//                name.setFocusable(true);
//                name.setCursorVisible(true);
//                name.setFocusableInTouchMode(true);

                name.setEnabled(true);
                name.setSelection(name.getText().length());
                name.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_FORCED);
            }
        });

        iv_edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mobile.setClickable(true);
//                mobile.setFocusable(true);
//                mobile.setCursorVisible(true);
//                mobile.setFocusableInTouchMode(true);

                mobile.setEnabled(true);
                mobile.setSelection(mobile.getText().length());
                mobile.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mobile, InputMethodManager.SHOW_FORCED);
            }
        });

        ll_done_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String mobile1 = mobile.getText().toString().trim();

                if (name1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.name_can_not_be_empty), Toast.LENGTH_SHORT).show();
                } else if (mobile1.equals("")) {
                    Toast.makeText(ProfileActivity.this, ProfileActivity.this.getResources().getString(R.string.phone_can_not_be_empty), Toast.LENGTH_SHORT).show();
                } else {
                    accountModule.editProfile(driverid, name1, mobile1, imagePathCompressed, driver_token, language_id);
                }
            }
        });
    }

    public void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == Activity.RESULT_OK) {
            try {
                if (req == 101) {
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);

                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressMode.compressImage(imagePath);

                    // imagePathCompressed = compressImage(imagePath);
                    Logger.e("image Compreed        " + imagePathCompressed);

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    final int REQUIRED_SIZE = 300;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bitmap1 = BitmapFactory.decodeFile(filePath, options);
                    iv_profile_pic_upload.setImageBitmap(bitmap1);
                }
            } catch (Exception e) {
                Logger.e("res         " + e.toString());
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void showlogoutdialog() {

        dialog = new Dialog(ProfileActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_logout);

        LinearLayout yes = (LinearLayout) dialog.findViewById(R.id.yes);
        LinearLayout no = (LinearLayout) dialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accountModule.logoutApi(driverid, driver_token, language_id);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onAPIRunningState(int a) {

        if (a == ApiFetcher.KEY_API_IS_RUNNING) {
            pd.show();
        }
        if (a == ApiFetcher.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (apiName.equals("Edit Profile")) {

            Register register = new Register();
            register = gson.fromJson(response + "", Register.class);

            if (register.getResult().toString().equals("1")) {
                new SessionManager(this).createLoginSession(register.getDetails().getDriverId(),register.getDetails().getDriverName(),register.getDetails().getDriverPhone(),register.getDetails().getDriverEmail(),register.getDetails().getDriverImage(),register.getDetails().getDriverPassword(),register.getDetails().getDriverToken(),register.getDetails().getDeviceId(),register.getDetails().getFlag(),register.getDetails().getRating(),register.getDetails().getCarTypeId(),register.getDetails().getCarModelId(),
                        register.getDetails().getCarNumber(),register.getDetails().getCityId(),register.getDetails().getRegisterDate(),register.getDetails().getLicense(),register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",register.getDetails().getCompletedRides(), register.getDetails().getRejectRides(),register.getDetails().getCancelledRides(),
                        register.getDetails().getLoginLogout(),register.getDetails().getBusy(),register.getDetails().getOnlineOffline(),register.getDetails().getDetailStatus(),register.getDetails().getStatus(),register.getDetails().getCarTypeName(),register.getDetails().getCarModelName());
                finish();
            } else if (register.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + register.getMsg());
            } else {
                Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Logout")) {
            Log.d("***** LOGOUT_API " , ""+response);
            DeviceId deviceId = new DeviceId();
            deviceId = gson.fromJson(response, DeviceId.class);

            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(ProfileActivity.this, SplashActivity.class));
                finish();
                MainActivity.mainActivity.finish();


//                firebasutil.logOutDriver();
                firebasutil.setDriverOnlineStatus(false);
                firebasutil.setDriverLoginLogoutStatus(false);
                sessionManager.logoutUser();
                dialog.dismiss();
            } else if (deviceId.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + deviceId.getMsg());
            } else {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }
}