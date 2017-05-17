package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.manager.DeviceManager;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.DriverLocation;
import com.apporio.demotaxiappdriver.models.about.About;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.others.AerialDistance;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.Constants;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.others.Maputils;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.apporio.demotaxiappdriver.parsing.HelpModule;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import com.apporio.demotaxiappdriver.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements
        ApiFetcher,
        Apis,
        OnMapReadyCallback {


    DatabaseReference mDatabaseReference;

    Marker mm;

    public static Activity mainActivity;
    TextView tv_address, tv_car_name, tv_name, tv_car_number, tv_profile_email, tv_profile_name, speed_txt, time_txt;
    ImageView iv_profile_pic;
    SwitchCompat switchCompat;

    String driverId, language_id, ride_id, ride_status, driver_token, driverName, switch1 = "0",
            car_type_name, car_model_name, car_type_id, carNumber, driverEmail,
            driverImage, callSupport, deviceId, status = "Offline", android_id;

    GoogleMap mGooglemap;

    String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

    AerialDistance aerialDistance;
    ProgressDialog pd;
    DriverModule driverModule;
    SessionManager sessionManager;
    LocationSession app_location_manager;
    DeviceManager deviceManager;

    LanguageManager languageManager;

    FirebaseUtils firebaseutil;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("Drivers_A");
        firebaseutil = new FirebaseUtils(this);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        menuListeners();
        mainActivity = this;
        Constants.mainActivity = 0;


        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_car_name = (TextView) findViewById(R.id.tv_car_name);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_car_number = (TextView) findViewById(R.id.tv_car_number);
        switchCompat = (SwitchCompat) findViewById(R.id.switch1);
        speed_txt = (TextView) findViewById(R.id.speed_txt);
        time_txt = (TextView) findViewById(R.id.time_txt);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        driverModule = new DriverModule(this);
        aerialDistance = new AerialDistance();
        languageManager = new LanguageManager(this);
        sessionManager = new SessionManager(this);
        app_location_manager = new LocationSession(this);
        deviceManager = new DeviceManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        pd = new ProgressDialog(this);
        pd.setMessage("" + this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        iv_profile_pic = (ImageView) findViewById(R.id.iv_profile_pic);
        tv_profile_name = (TextView) findViewById(R.id.tv_profile_name);
        tv_profile_email = (TextView) findViewById(R.id.tv_profile_email);

        driverId = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        driverName = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        driverEmail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverImage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);
        carNumber = sessionManager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number);
        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);
        car_type_name = sessionManager.getUserDetails().get(SessionManager.KEY_CarTypeName);
        car_model_name = sessionManager.getUserDetails().get(SessionManager.KEY_CarModelName);
        car_type_id = sessionManager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId);
        deviceId = FirebaseInstanceId.getInstance().getToken();
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        tv_name.setText("" + driverName);
        tv_car_name.setText("" + car_type_name + " | " + car_model_name);
        tv_car_number.setText("" + carNumber);
        tv_profile_name.setText(driverName);
        tv_profile_email.setText(driverEmail);
        Picasso.with(this)
                .load(Config.app_config.getResponse().getDriver_config().getBase_url() + driverImage)
                .placeholder(R.drawable.dummy_pic)
                .error(R.drawable.dummy_pic)
                .fit()
                .into(iv_profile_pic);

        AccountModule accountModule = new AccountModule(this);
        accountModule.deviceIdApi(driverId, deviceId, driver_token, language_id);

        HelpModule helpModule = new HelpModule(this);
        helpModule.callSupportApi(language_id);





        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    switch1 = "0";
                    driverModule.offlineApi(driverId, "2", driver_token, language_id);
                    sessionManager.setonline_offline(false);
                    firebaseutil.setDriverOnlineStatus(false);
                } else {
                    switch1 = "1";
                    driverModule.onlineApi(driverId, "1", driver_token, language_id);
                    firebaseutil.setDriverOnlineStatus(true);
                    sessionManager.setonline_offline(true);
                }
            }
        });

        if ((ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        }


        try {
            onNewIntent(getIntent());
        } catch (Exception e) {
            Logger.e("Exception in OnNewIntent      " + e.toString());
        }


        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version_name = info.versionName;
        int version_code = info.versionCode;

        DriverModule customerModule = new DriverModule(this);
        customerModule.updateApi(version_code + "", language_id);


    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        Constants.mainActivity = 0;
        driverModule.onlineApiStart(driverId, "1", driver_token, language_id);
        driverName = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME);
        driverEmail = sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail);
        driverImage = sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage);
        tv_profile_name.setText(driverName);
        tv_profile_email.setText(driverEmail);
        tv_name.setText("" + driverName);
        Picasso.with(this)
                .load(Config.app_config.getResponse().getDriver_config().getBase_url() + driverImage)
                .placeholder(R.drawable.dummy_pic)
                .error(R.drawable.dummy_pic)
                .fit()
                .into(iv_profile_pic);


        if (sessionManager.isLoggedIn()) {
            mDatabaseReference.child(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DriverLocation driver_location = dataSnapshot.getValue(DriverLocation.class);
                    try {
                        if (!driver_location.driver_admin_status.equals("1")) {
                            sessionManager.logoutUser();
                            startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        }
                    } catch (Exception e) {
                        Log.d("Exception occur ", "" + e);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Logger.e("databaseError     " + databaseError);
                }
            });
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Constants.mainActivity = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        Constants.mainActivity = 1;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Constants.mainActivity = 1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.mainActivity = 1;

    }


    ///// API FETCHER
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
        DeviceId deviceIdnew;
        deviceIdnew = gson.fromJson(response, DeviceId.class);

        if (deviceIdnew.getResult().toString().equals("419")) {
            sessionManager.logoutUser();
            mGooglemap.setOnMyLocationChangeListener(null);
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();
            Logger.e("lat long update       " + deviceIdnew.getMsg());
        } else {
            if (apiName.equals("Update Lat Long")) {
                DeviceId deviceId;
                deviceId = gson.fromJson(response, DeviceId.class);
                if (deviceId.getResult().toString().equals("1")) {
                    Logger.e("lat long update       " + deviceId.getMsg());
                }
            }

            if (apiName.equals("Online")) {
                DeviceId deviceToken;
                deviceToken = gson.fromJson(response, DeviceId.class);

                if (deviceToken.getResult().toString().equals("1")) {
//                online.setVisible(false);
//                offline.setVisible(true);
                    status = "Online";
                    Toast toast = Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            if (apiName.equals("Online Start")) {
                DeviceId deviceToken;
                deviceToken = gson.fromJson(response, DeviceId.class);

                if (deviceToken.getResult().toString().equals("1")) {
                    switchCompat.setChecked(true);
//                online.setVisible(false);
//                offline.setVisible(true);
//                status = "Online";
                    firebaseutil.setDriverOnlineStatus(true);
                    sessionManager.setonline_offline(true);
                    Toast toast = Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            if (apiName.equals("Offline")) {
                DeviceId deviceToken;
                deviceToken = gson.fromJson(response, DeviceId.class);

                if (deviceToken.getResult().toString().equals("1")) {
                    status = "Offline";
//                online.setVisible(true);
//                offline.setVisible(false);
                    Toast toast = Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            if (apiName.equals("Offline Finish")) {
                DeviceId deviceToken;
                deviceToken = gson.fromJson(response, DeviceId.class);

                if (deviceToken.getResult().toString().equals("1")) {
//                Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "" + deviceToken.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            if (apiName.equals("Update")) {

                DeviceId deviceId;
                deviceId = gson.fromJson(response, DeviceId.class);

                if (deviceId.getResult().toString().equals("1")) {
                    Logger.e("success" + deviceId.getMsg());
                } else if (deviceId.getResult().toString().equals("418")) {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        dialogForUpdate();
                                    }
                                });

                            } catch (Exception e) {
                                Logger.e("Exception Caught in Thread      " + e.toString());
                            }
                        }
                    };
                    Thread t = new Thread(r);
                    t.start();
                } else {
                    Logger.e("err a gyi" + deviceId.getMsg());
                }
            }

            if (apiName.equals("Device Id")) {
                DeviceId deviceid;
                deviceid = gson.fromJson(response, DeviceId.class);
                if (deviceid.getResult().toString().equals("1")) {
                    Logger.e("device Id     " + deviceid.getMsg());
                } else {
                    Toast.makeText(this, "" + deviceid.getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            if (apiName.equals("Call Support")) {
                About about;
                about = gson.fromJson(response, About.class);
                if (about.getResult().toString().equals("1")) {
                    callSupport = about.getDetails().getDescription();
                }
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
//        else if (status.equals("Online")) {
//            driverModule.offlineApiFinish(driverId, "2", driver_token, language_id);
//        }
        else {
            super.onBackPressed();
        }
    }


    private void menuListeners() {
        findViewById(R.id.profile_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        findViewById(R.id.my_rides_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RidesActivity.class));
            }
        });

        findViewById(R.id.my_earnings_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EarningActivity.class));
            }
        });


        findViewById(R.id.customer_support_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomerSupportActivity.class));
            }
        });

        findViewById(R.id.terms_and_condition_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TcActivity.class));
            }
        });

        findViewById(R.id.about_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGooglemap = googleMap;
        MapsInitializer.initialize(this);

        ////////  setting my location enable
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGooglemap.setMyLocationEnabled(true);



        if(!app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT).equals("")){
            Maputils.moverCamera(mGooglemap , new LatLng(Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))) );
            tv_address.setText(""+app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
            driverModule.updateLatLongApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT) + "", app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG) + "", app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT), sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
        }

        mGooglemap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                app_location_manager.setLocationLatLong(""+mGooglemap.getCameraPosition().target.latitude , ""+mGooglemap.getCameraPosition().target.longitude);
                firebaseutil.updateLocation_with_text();
            }
        });

    }







    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationEvent event){
        Maputils.moverCamera(mGooglemap , new LatLng(Double.parseDouble(event.getlatitude_string()) , Double.parseDouble(event.getLongitude_string())) );
//        setOrAnimateMarker();

        tv_address.setText(""+event.getFullAddress());
        driverModule.updateLatLongApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), event.getlatitude_string() + "", event.getLongitude_string() + "", event.getFullAddress(), sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
        speed_txt.setText(""+event.getCurrent_speed());
        time_txt.setText(""+event.getCurrent_time());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    finish();
                } else if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    mapView.getMapAsync(this);
                }
            }
        }
    }

    public void dialogForUpdate() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_update);
        dialog.setCancelable(true);

        LinearLayout ll_update_formal = (LinearLayout) dialog.findViewById(R.id.ll_update_formal);
        LinearLayout ll_remind_me_later = (LinearLayout) dialog.findViewById(R.id.ll_remind_me_later);

        ll_update_formal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                Logger.e("package name      " + appPackageName);
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        ll_remind_me_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            ride_status = extras.getString("ride_status");
            ride_id = extras.getString("ride_id");

            if (ride_status.equals("1")) {

                Intent i = new Intent();
                i.setClassName("com.apporio.demotaxiappdriver", "com.apporio.demotaxiappdriver.ReceivePassengerActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("rideId", ride_id);
                startActivity(i);
            } else if (ride_status.equals("2")) {
                Intent i = new Intent();
                i.setClassName("com.apporio.demotaxiappdriver", "com.apporio.demotaxiappdriver.RidesActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            } else if (ride_status.equals("8")) {
                Intent i = new Intent();
                i.setClassName("com.apporio.demotaxiappdriver", "com.apporio.demotaxiappdriver.ReceivePassengerActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("rideId", ride_id);
                startActivity(i);
            }
        }
    }




    private void setOrAnimateMarker() {
        if(mm == null){
            setMarker();
        }else {
            animateMarker(new LatLng(Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))), Float.parseFloat(""+app_location_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR)));
        }
    }

    public void setMarker() {
        MarkerOptions marker_option = new MarkerOptions()
                .position(new LatLng(Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)), Double.parseDouble(app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)))).flat(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_minicar_60));
        mm =  mGooglemap.addMarker(marker_option);
    }
    public  void animateMarker(final LatLng toPosition , float bearingfactor) {

        rotateMarker(mm,bearingfactor);

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mGooglemap.getProjection();
        Point startPoint = proj.toScreenLocation(mm.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 1000;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                mm.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (false) {
                        mm.setVisible(false);
                    } else {
                        mm.setVisible(true);
                    }
                }
            }
        });
    }
    static public void rotateMarker(final Marker marker, final float toRotation) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final float startRotation = marker.getRotation();
        final long duration = 1000;

        final Interpolator interpolator = new LinearInterpolator();



        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);

                float rot = t * toRotation + (1 -t) * startRotation;

                marker.setRotation(-rot > 180 ? rot/2 : rot);
                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
    }



}