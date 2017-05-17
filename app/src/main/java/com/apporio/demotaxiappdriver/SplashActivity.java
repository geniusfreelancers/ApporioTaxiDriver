package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.DeviceManager;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ConfigModelResponse;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.ConnectionDetector;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import com.apporio.demotaxiappdriver.logger.Logger;

public class SplashActivity extends AppCompatActivity implements ApiFetcher {
    LinearLayout LoginBox, ll_register_splash, ll_login_splash;
    CoordinatorLayout cl_splash;
    public static Activity splash;
    SessionManager sessionManager;

    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    public static int gallery_grid_Images[] = {R.drawable.splash1, R.drawable.splash2, R.drawable.splash3};
    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    String CALL_PHONE = "android.permission.CALL_PHONE";
    String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    int temp;
    int version_code;

    DriverModule driverModule;
    ProgressDialog pd;
    DeviceManager deviceManager;
    LanguageManager languageManager;
    String detail_status;

    String language_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startService(new Intent(this, TimeService.class));
        splash = this;
        pd = new ProgressDialog(this);
        pd.setMessage(SplashActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        deviceManager = new DeviceManager(this);
        sessionManager = new SessionManager(this);
        driverModule = new DriverModule(this);
        languageManager = new LanguageManager(this);


        driverModule.setAppConfig();
    }


    public void initiateSplashProcess(){
        String lang = Locale.getDefault().getDisplayLanguage();
        Logger.e("language      " + lang);
        if (lang.equals("français")) {
            language_id = "2";
            Logger.e("language_id       " + "" + lang);
            languageManager.createLanguageSession(language_id);
        } else {
            language_id = "1";
            Logger.e("language_id       " + "" + lang);
            languageManager.createLanguageSession(language_id);
        }

        LoginBox = (LinearLayout) findViewById(R.id.LoginBox);
        ll_register_splash = (LinearLayout) findViewById(R.id.ll_register_splash);
        ll_login_splash = (LinearLayout) findViewById(R.id.ll_login_splash);
        cl_splash = (CoordinatorLayout) findViewById(R.id.cl_splash);

        if (sessionManager.isLoggedIn()) {

            detail_status = sessionManager.getUserDetails().get(SessionManager.KEY_Detail_Status);
            if (detail_status.equals("1")) {
                temp = 1;
                LoginBox.setVisibility(View.VISIBLE);
            } else if (detail_status.equals("2")) {
                temp = 0;
                LoginBox.setVisibility(View.GONE);
            }
        } else if (!(sessionManager.isLoggedIn())) {
            temp = 1;
            LoginBox.setVisibility(View.VISIBLE);
        }

        if ((ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SplashActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SplashActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.CALL_PHONE", "android.permission.READ_EXTERNAL_STORAGE"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SplashActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.CALL_PHONE"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SplashActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.CALL_PHONE", "android.permission.READ_EXTERNAL_STORAGE"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(SplashActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_EXTERNAL_STORAGE"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.CALL_PHONE"}, 1);
        } else if ((ContextCompat.checkSelfPermission(SplashActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
        } else {
            checkNetworkStatus();
        }

        ll_login_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });

        ll_register_splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });
    }

    public void checkNetworkStatus() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            Logger.e("SUCCESS       " + "All location settings are satisfied.");

                            PackageManager manager = SplashActivity.this.getPackageManager();
                            PackageInfo info = null;
                            try {
                                info = manager.getPackageInfo(SplashActivity.this.getPackageName(), 0);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                            String version_name = info.versionName;
                            version_code = info.versionCode;

                            Logger.e("code      " + version_code);
                            driverModule.forceUpdateApi(version_code + "", language_id);

                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Logger.e("RESOLUTION_REQUIRED       " + "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the result in onActivityResult().
                                status.startResolutionForResult(SplashActivity.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                Logger.e("Exception         " + "PendingIntent unable to execute request.");
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Logger.e("SETTINGS_CHANGE_UNAVAILABLE       " + "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                            break;
                    }
                }
            });


        } else {
            Snackbar snackbar = Snackbar
                    .make(cl_splash, SplashActivity.this.getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                    .setDuration(Snackbar.LENGTH_INDEFINITE)
                    .setActionTextColor(Color.RED)
                    .setAction(""+SplashActivity.this.getResources().getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkNetworkStatus();
                        }
                    });
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            int len = permissions.length;
            checkNetworkStatus();
        }
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

        if (apiName.equals("Force Update")) {

            DeviceId deviceId;
            deviceId = gson.fromJson(response, DeviceId.class);

            if (deviceId.getResult().toString().equals("1")) {
//                Logger.e("success" + deviceId.getMsg());

//                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                if (temp == 0) {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                SplashActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
//                                        if (detail_status.equals("1")) {
//                                            startActivity(new Intent(SplashActivity.this, DocumentActivity.class));
//                                            finish();
//                                        } else if (detail_status.equals("2")) {
                                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                        finish();
//                                        }
                                    }
                                });
                            } catch (Exception e) {
                                Logger.e("Exception Caught in Thread      " + e.toString());
                            }
                        }
                    };
                    Thread t = new Thread(r);
                    t.start();
                } else if (temp == 1) {
// Ignore This Part
                }
            } else if (deviceId.getResult().toString().equals("418")) {
                dialogForAppUpdate();
            } else {

            }
        }

        if(apiName.equals("api_config")){
            ConfigResultCheck resultCheck = new ConfigResultCheck();
            resultCheck = gson.fromJson(""+response , ConfigResultCheck.class);
//            Toast.makeText(splashActivity, "Fetching Config = "+resultCheck.getResult(), Toast.LENGTH_SHORT).show();
            if(resultCheck.getResult() == 1 ){
                initiateSplashProcess();
                Config.app_config = gson.fromJson(""+response, ConfigModelResponse.class);
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    public void dialogForAppUpdate() {

        Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_force_update);
        dialog.setCancelable(false);

        LinearLayout ll_update = (LinearLayout) dialog.findViewById(R.id.ll_update);
        ll_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = getPackageName();
                Logger.e("package name      " + appPackageName);
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Logger.e("RESULT_OK       " + "User agreed to make required location settings changes.");

                        PackageManager manager = SplashActivity.this.getPackageManager();
                        PackageInfo info = null;
                        try {
                            info = manager.getPackageInfo(SplashActivity.this.getPackageName(), 0);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        String version_name = info.versionName;
                        version_code = info.versionCode;

                        Logger.e("code      " + version_code);
                        driverModule.forceUpdateApi(version_code + "", language_id);

                        break;
                    case Activity.RESULT_CANCELED:
                        Logger.e("RESULT_CANCELED       " + "User chose not to make required location settings changes.");
                        finish();
                        break;
                }
                break;
        }
    }
}