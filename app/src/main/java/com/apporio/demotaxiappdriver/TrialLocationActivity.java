package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.LocationBaseActivity;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.LocationConfiguration;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.constants.ProviderType;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.DeviceManager;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.DriverLocation;
import com.apporio.demotaxiappdriver.models.about.About;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.others.*;
import com.apporio.demotaxiappdriver.others.AerialDistance;
import com.apporio.demotaxiappdriver.parsing.AccountModule;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.apporio.demotaxiappdriver.parsing.HelpModule;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;


public class TrialLocationActivity extends LocationBaseActivity implements
        ApiFetcher,
        Apis,
        OnMapReadyCallback,
        LocationListener {



    DatabaseReference mDatabaseReference;
    public static Activity mainActivity;
    TextView tv_address, tv_car_name, tv_name, tv_car_number, tv_profile_email, tv_profile_name;
    ImageView iv_profile_pic;
    SwitchCompat switchCompat;
    String  language_id, ride_id, ride_status, driver_token, switch1 = "0",  callSupport, deviceId, status = "Offline", location1 = "";

    GoogleMap map;
    Geocoder geocoder;
    List<Address> addresses;

    String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

    com.apporio.demotaxiappdriver.others.AerialDistance aerialDistance;
    ProgressDialog pd;
    DriverModule driverModule;
    SessionManager sessionManager;
    LocationSession app_location_manager ;
    DeviceManager deviceManager;
    LanguageManager languageManager;
//    RideSession rideSession ;
    FirebaseUtils firebaseutil ;
    FirebaseDatabase database;
    private boolean reverse_geocode_process = false ; 


//////// location work
    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new LocationConfiguration()
                .keepTracking(true)
                .askForGooglePlayServices(true)
                .setMinAccuracy(200.0f)
                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
                .setWaitPeriod(ProviderType.GPS, 5 * 1000)
                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
                .setWaitPeriod(ProviderType.DEFAULT_PROVIDERS , 5*1000)
                .setGPSMessage("This app requires GPS to work properly !")
                .setRationalMessage("Please turn on Permission");
    }

    @Override
    public void onLocationFailed(int failType) {

        Toast.makeText(this, "Please Check Your internet Connectivity. "+failType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(reverse_geocode_process == false){
//            rideSession.setCurrentLocation(""+location.getLatitude() , ""+location.getLongitude());
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(rideSession.getCurrentLocation()).zoom(17).build();
//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            new GetLocationAsync(location.getLatitude(), location.getLongitude()).execute();
        }
    }


    public void init(){
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_car_name = (TextView) findViewById(R.id.tv_car_name);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_car_number = (TextView) findViewById(R.id.tv_car_number);
        switchCompat = (SwitchCompat) findViewById(R.id.switch1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("Drivers_A");
        firebaseutil = new FirebaseUtils(this);
//        rideSession = new RideSession(this);
        setContentView(R.layout.activity_main);
        init();
        menuListeners();
        mainActivity = this;
        Constants.mainActivity = 0;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        driverModule = new DriverModule(this);
        aerialDistance = new AerialDistance();
        languageManager = new LanguageManager(this);
        sessionManager = new SessionManager(this);
        app_location_manager = new LocationSession(this);
        deviceManager = new DeviceManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        iv_profile_pic = (ImageView) findViewById(R.id.iv_profile_pic);
        tv_profile_name = (TextView) findViewById(R.id.tv_profile_name);
        tv_profile_email = (TextView) findViewById(R.id.tv_profile_email);

        deviceId = FirebaseInstanceId.getInstance().getToken();



        tv_name.setText("" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME));
        tv_car_name.setText("" + sessionManager.getUserDetails().get(SessionManager.KEY_CarTypeName) + " | " + sessionManager.getUserDetails().get(SessionManager.KEY_CarModelName));
        tv_car_number.setText("" + sessionManager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number));
        tv_profile_name.setText(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME));
        tv_profile_email.setText(sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail));
        Picasso.with(this)
                .load(Config.app_config.getResponse().getDriver_config().getBase_url() + sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage))
                .placeholder(R.drawable.dummy_pic)
                .error(R.drawable.dummy_pic)
                .fit()
                .into(iv_profile_pic);

        AccountModule accountModule = new AccountModule(this);
        accountModule.deviceIdApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), deviceId, sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));

        HelpModule helpModule = new HelpModule(this);
        helpModule.callSupportApi(languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));



        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    switch1 = "0";
                    driverModule.offlineApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), "2", sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                    sessionManager.setonline_offline(false);
                    firebaseutil.setDriverOnlineStatus(false);
                } else {
                    switch1 = "1";
                    driverModule.onlineApi(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), "1", sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                    firebaseutil.setDriverOnlineStatus(true);
                    sessionManager.setonline_offline(true);
                }
            }
        });

        if ((ContextCompat.checkSelfPermission(TrialLocationActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(TrialLocationActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else {
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
        customerModule.updateApi(version_code + "", languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));


    }



    private void menuListeners(){
        findViewById(R.id.profile_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this, ProfileActivity.class));
            }
        });

        findViewById(R.id.my_rides_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this, RidesActivity.class));
            }
        });

        findViewById(R.id.my_earnings_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this, EarningActivity.class));
            }
        });


        findViewById(R.id.customer_support_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this , CustomerSupportActivity.class));
            }
        });

        findViewById(R.id.terms_and_condition_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this , TcActivity.class));
            }
        });

        findViewById(R.id.about_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrialLocationActivity.this, AboutActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
        Constants.mainActivity = 0;
        driverModule.onlineApiStart(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), "1", sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
        tv_profile_name.setText(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME));
        tv_profile_email.setText(sessionManager.getUserDetails().get(SessionManager.KEY_DriverEmail));
        tv_name.setText("" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME));
        Picasso.with(this)
                .load(Config.app_config.getResponse().getDriver_config().getBase_url() + sessionManager.getUserDetails().get(SessionManager.KEY_DriverImage))
                .placeholder(R.drawable.dummy_pic)
                .error(R.drawable.dummy_pic)
                .fit()
                .into(iv_profile_pic);


        if(sessionManager.isLoggedIn()){
            mDatabaseReference.child(sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DriverLocation driver_location = dataSnapshot.getValue(DriverLocation.class);
                    try {
                        if(!driver_location.driver_admin_status.equals("1")){
                            sessionManager.logoutUser();
                            startActivity(new Intent(TrialLocationActivity.this , SplashActivity.class ));
                        }
                    }catch (Exception e){
                        Log.d("Exception occur " , ""+e);
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

        if(deviceIdnew.getResult().toString().equals("419")){
            sessionManager.logoutUser();
            map.setOnMyLocationChangeListener(null);
            Intent intent = new Intent(TrialLocationActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();
            Logger.e("lat long update       " + deviceIdnew.getMsg());
        }
        else{
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
                                TrialLocationActivity.this.runOnUiThread(new Runnable() {
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        MapsInitializer.initialize(this);
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)) {
            case ConnectionResult.SUCCESS:
                map.setTrafficEnabled(true);
                map.setIndoorEnabled(true);
                map.getUiSettings().setZoomControlsEnabled(false);
                map.getUiSettings().setZoomGesturesEnabled(false);
                map.getUiSettings().setCompassEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(true);
                map.getUiSettings().setRotateGesturesEnabled(false);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.getUiSettings().setScrollGesturesEnabled(false);
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(this, ""+this.getResources().getString(R.string.service_missing), Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(this, ""+this.getResources().getString(R.string.update_required), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, GooglePlayServicesUtil.isGooglePlayServicesAvailable(this), Toast.LENGTH_SHORT).show();
        }
    }



    private class GetLocationAsync extends AsyncTask<String, Void, String> {

        double x, y;
        StringBuilder str;

        public GetLocationAsync(double latitude, double longitude) {
            x = latitude;
            y = longitude;
        }

        @Override
        protected void onPreExecute() {
            reverse_geocode_process = true ;
            if (location1.equals("")) {
                tv_address.setText(""+TrialLocationActivity.this.getResources().getString(R.string.getting_location));
            } else {

            }
        }

        @Override
        protected String doInBackground(String... params) {
            str = new StringBuilder();
            try {
                geocoder = new Geocoder(TrialLocationActivity.this, Locale.ENGLISH);
                addresses = geocoder.getFromLocation(x, y, 1);
                if (geocoder.isPresent()) {
                    Address returnAddress = addresses.get(0);
                    String addressLine = returnAddress.getAddressLine(0);
                    String addressLine1 = returnAddress.getAddressLine(1);
                    String addressLine2 = returnAddress.getAddressLine(2);
                    str.append(addressLine + ", " + addressLine1 + ", " + addressLine2);
//                    app_location_manager.setLocationAddress(""+str);
                    firebaseutil.updateLocation_with_text();
//                    driverModule.updateLatLongApi(sessionManager.getCurrentRideDetails().get(SessionManager.KEY_DRIVER_ID), rideSession.getCurrentLocation().latitude + "", rideSession.getCurrentLocation().longitude + "", location1, sessionManager.getCurrentRideDetails().get(SessionManager.KEY_DriverToken), languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                } else {
                    Logger.e("Geocoder " + "not Present");
                }
            } catch (Exception e) {
                Logger.e("Exception In Background method        " + e.toString());
            }
            return str.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            reverse_geocode_process = false ;
            try {
                if (result.equals("")) {
//                    new GetLocationAsync(currentLat, currentLong).execute();
                } else {
                    if (result.equals(location1)) {
                        location1 = result;
                    } else {
                        location1 = result;
                        tv_address.setText(result);
                    }
                }
            } catch (Exception e) {
                Log.e("err", e.toString());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    finish();
                } else if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
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



}