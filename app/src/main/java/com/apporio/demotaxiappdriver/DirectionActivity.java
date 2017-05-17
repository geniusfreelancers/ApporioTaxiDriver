package com.apporio.demotaxiappdriver;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.adapter.ReasonAdapter;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.cancelreasoncustomer.CancelReasonCustomer;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.models.ridearrived.RideArrived;
import com.apporio.demotaxiappdriver.models.updatelatlongbeforearrived.UpdateLatLongBeforeArrived;
import com.apporio.demotaxiappdriver.others.AerialDistance;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.Constants;
import com.apporio.demotaxiappdriver.others.DownloadTask;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.apporio.demotaxiappdriver.parsing.RideModule;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import com.apporio.demotaxiappdriver.logger.Logger;

public class DirectionActivity extends AppCompatActivity implements ApiFetcher, OnMapReadyCallback {

    TextView tv_pickup_location, tv_drop_location;
    LinearLayout ll_arrived, ll_begin_trip, ll_end_trip, ll_pickup_location, ll_drop_location;

    public static DirectionActivity directionActivity;

    BroadcastReceiver broadcastReceiver;

    public static GoogleMap mGoogleMap;
    ArrayList<LatLng> mMarkerPoints;
    double driver_current_lat, driver_current_long;
    LocationRequest mLocationRequest;
    private List<Address> addresses;
    private Geocoder geocoder;
    MapView mapView;


    int arrived_sec, begin_sec, end_sec;

    CancelReasonCustomer cancelReasonCustomer;

    double begin_lat, begin_long, end_lat, end_long;
    //    public static String begin_address, end_address;
    String waiting_time = "", ride_time, arrived_time, begin_time, end_time, begin_location, end_location, ride_status;
    String pickupLat, pickupLong, pickupAddress, customerId, rideId, driverId, dropLat, dropLong, dropAddress;

    SessionManager sessionManager;
    ProgressDialog pd;
    DriverModule driverModule;
    RideModule rideModule;
    AerialDistance aerialDistance;

    String reason_id;

    //    TextView tv_timer, tv_cancel_by_driver;
    Timer timer;
    int count, i = 0;

    //    LinearLayout ll_timer, ll_cancel_by_driver;
    Handler handler;
    Runnable r;
    public static FrameLayout fl_gps1;
    String driver_token;

    String demo = "1";

    Marker marker;
    LocationSession app_location_manager ;

    LanguageManager languageManager;
    String language_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app_location_manager = new LocationSession(this);
        setContentView(R.layout.activity_direction);
//        getSupportActionBar().hide();
        directionActivity = this;


        Constants.directionActivity = 0;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        pd = new ProgressDialog(this);
        pd.setMessage(DirectionActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_direction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        fl_gps1 = (FrameLayout) findViewById(R.id.fl_gps1);

        rideModule = new RideModule(this);
        driverModule = new DriverModule(this);
        sessionManager = new SessionManager(this);
        aerialDistance = new AerialDistance();

        handler = new Handler();

        timer = new Timer();

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);


        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

//        tv_timer = (TextView) findViewById(R.id.tv_timer1);
//        tv_cancel_by_driver = (TextView) findViewById(R.id.tv_cancel_by_driver);
        ll_pickup_location = (LinearLayout) findViewById(R.id.ll_pickup_location);
        ll_drop_location = (LinearLayout) findViewById(R.id.ll_drop_location);
        ll_arrived = (LinearLayout) findViewById(R.id.ll_arrived);
        ll_begin_trip = (LinearLayout) findViewById(R.id.ll_begin_trip);
        ll_end_trip = (LinearLayout) findViewById(R.id.ll_end_trip);
//        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        tv_pickup_location = (TextView) findViewById(R.id.tv_pickup_location);
        tv_drop_location = (TextView) findViewById(R.id.tv_drop_location);
//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mGooglemap);
//        mapFragment.getMapAsync(this);

        mapView = (MapView) findViewById(R.id.map_main);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

//        ll_timer = (LinearLayout) findViewById(R.id.ll_timer);
//        ll_cancel_by_driver = (LinearLayout) findViewById(R.id.ll_cancel_by_driver);

        rideId = super.getIntent().getExtras().getString("rideId");
        pickupLat = super.getIntent().getExtras().getString("pickupLat");
        pickupLong = super.getIntent().getExtras().getString("pickupLong");
        pickupAddress = super.getIntent().getExtras().getString("pickupAddress");
        dropLat = super.getIntent().getExtras().getString("dropLat");
        dropLong = super.getIntent().getExtras().getString("dropLong");
        dropAddress = super.getIntent().getExtras().getString("dropAddress");
        customerId = super.getIntent().getExtras().getString("customerId");
        driverId = super.getIntent().getExtras().getString("driverId");
        arrived_time = super.getIntent().getExtras().getString("arrived_time");
        ride_status = super.getIntent().getExtras().getString("ride_status");


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (ride_status.equals("3")) {

            tv_pickup_location.setText(pickupAddress);
            tv_drop_location.setText(dropAddress);
        } else if (ride_status.equals("5")) {

            tv_pickup_location.setText(pickupAddress);
            tv_drop_location.setText(dropAddress);


            ll_arrived.setVisibility(View.GONE);
            ll_begin_trip.setVisibility(View.VISIBLE);
            ll_pickup_location.setVisibility(View.GONE);
            ll_drop_location.setVisibility(View.VISIBLE);

        } else if (ride_status.equals("6")) {

            tv_pickup_location.setText(pickupAddress);
            tv_drop_location.setText(dropAddress);

            String beginLat = super.getIntent().getExtras().getString("begin_lat");
            String beginLong = super.getIntent().getExtras().getString("begin_long");

            Logger.e("begin Lat long String      " + beginLat + ", " + beginLong);

            begin_lat = Double.parseDouble(beginLat);
            begin_long = Double.parseDouble(beginLong);

            Logger.e("begin Lat long double        " + begin_lat + ", " + begin_long);

            arrived_time = super.getIntent().getExtras().getString("arrived_time");
            begin_location = super.getIntent().getExtras().getString("begin_location");
            begin_time = super.getIntent().getExtras().getString("begin_time");

            String[] h1 = arrived_time.split(":");
            int hours = Integer.parseInt(h1[0]);
            int minute = Integer.parseInt(h1[1]);
            int second = Integer.parseInt(h1[2]);
            arrived_sec = second + (60 * minute) + (3600 * hours);

            String[] h2 = begin_time.split(":");
            int hours2 = Integer.parseInt(h2[0]);
            int minute2 = Integer.parseInt(h2[1]);
            int second2 = Integer.parseInt(h2[2]);
            begin_sec = second2 + (60 * minute2) + (3600 * hours2);

            int waiting_sec = begin_sec - arrived_sec;

            int hours1 = waiting_sec / 3600;
            int minutes1 = (waiting_sec % 3600) / 60;
            int seconds1 = waiting_sec % 60;
            waiting_time = hours1 + ":" + minutes1 + ":" + seconds1;


            ll_arrived.setVisibility(View.GONE);
            ll_begin_trip.setVisibility(View.GONE);
            ll_end_trip.setVisibility(View.VISIBLE);
            ll_pickup_location.setVisibility(View.GONE);
            ll_drop_location.setVisibility(View.VISIBLE);



        }


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle b = intent.getExtras();
                String ride_id = b.getString("ride_id");
                String ride_status = b.getString("ride_status");
                if (ride_status.equals("2")) {
                    dialogForRideCancelByCustomer();
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("broadCastNameCancel"));


        fl_gps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                track(pickupLat, pickupLong, dropLat, dropLong);
            }
        });

        ll_arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);
                arrived_time = hour + ":" + minutes + ":" + sec;

                String h = arrived_time;
                String[] h1 = h.split(":");
                int hours = Integer.parseInt(h1[0]);
                int minute = Integer.parseInt(h1[1]);
                int second = Integer.parseInt(h1[2]);
                arrived_sec = second + (60 * minute) + (3600 * hours);

                rideModule.arrivedTripApi(rideId, driverId, arrived_time, driver_token, language_id);

//                mMarkerPoints.clear();
//                mGoogleMap.clear();
//                begin_lat = gps.getLatitude();
//                begin_long = gps.getLongitude();

//                driver_current = new LatLng(begin_lat, begin_long);
//                drawMarker(driver_current);
//
//                drop_lat = Double.parseDouble(dropLat);
//                drop_long = Double.parseDouble(dropLong);
//                drop = new LatLng(drop_lat, drop_long);
//                drawMarker(drop);
//
//                try {
//                    new GetLocationAsync(begin_lat, begin_long).execute();
//                } catch (Exception e) {
//                    Log.e("err", e.toString());
//                }
            }
        });

        ll_begin_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogForRideStart();
            }
        });

        ll_end_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int mins = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);
                end_time = hour + ":" + mins + ":" + sec;

                String h = end_time;
                String[] h1 = h.split(":");
                int hours = Integer.parseInt(h1[0]);
                int minute = Integer.parseInt(h1[1]);
                int second = Integer.parseInt(h1[2]);
                end_sec = second + (60 * minute) + (3600 * hours);

                int ride_sec = end_sec - begin_sec;

                int hours1 = ride_sec / 3600;
                int minutes1 = (ride_sec % 3600) / 60;
                int seconds1 = ride_sec % 60;
                ride_time = hours1 + ":" + minutes1 + ":" + seconds1;
                rideModule.endTripApi(rideId, driverId, String.valueOf(begin_lat), String.valueOf(begin_long), begin_location, app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT), app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG), app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT), end_time, waiting_time, ride_time, driver_token, language_id);

            }
        });

    }


    public void dialogForRideCancelByCustomer() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_cancelride);
        dialog.setCancelable(false);

        LinearLayout ll_ok_cancel = (LinearLayout) dialog.findViewById(R.id.ll_ok_cancel);
        ll_ok_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                startActivity(new Intent(DirectionActivity.this, RidesActivity.class));
            }
        });
        dialog.show();
    }


    public void track(String pickup_Lat, String pickup_Long, String drop_Lat, String drop_Long) {

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" + "saddr=" + pickup_Lat + "," + pickup_Long + "&daddr=" + drop_Lat + "," + drop_Long));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_direction, menu);
//        online = menu.findItem(R.id.online);
//        offline = menu.findItem(R.id.offline);
//        offline.setVisible(false);
        return true;
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.user_info) {
            startActivity(new Intent(DirectionActivity.this, InfoActivity.class)
                    .putExtra("pickupAddress", pickupAddress)
                    .putExtra("customerId", customerId));
            return true;
        }
        if (id == R.id.cancel_ride) {
            rideModule.cancelReasonApi(language_id);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void dialogForRideStart() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_for_start_ride);
        dialog.setCancelable(false);

        LinearLayout yes = (LinearLayout) dialog.findViewById(R.id.yes);
        LinearLayout no = (LinearLayout) dialog.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minut = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);
                begin_time = hour + ":" + minut + ":" + sec;

                String h = begin_time;
                String[] h1 = h.split(":");
                int hours = Integer.parseInt(h1[0]);
                int minute = Integer.parseInt(h1[1]);
                int second = Integer.parseInt(h1[2]);

                begin_sec = second + (60 * minute) + (3600 * hours);

                int waiting_sec = begin_sec - arrived_sec;

                int hours1 = waiting_sec / 3600;
                int minutes1 = (waiting_sec % 3600) / 60;
                int seconds1 = waiting_sec % 60;
                waiting_time = hours1 + ":" + minutes1 + ":" + seconds1;

                mGoogleMap.clear();
                mMarkerPoints.clear();

                rideModule.beginTripApi(rideId, driverId, app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT), app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG), app_location_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT), begin_time, driver_token, language_id);

                dialog.dismiss();
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



    private void drawMarker(LatLng point) {
        mMarkerPoints.add(point);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        if (mMarkerPoints.size() == 1) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_ola));
            marker = mGoogleMap.addMarker(markerOptions);
        } else if (mMarkerPoints.size() == 2) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_small));
            mGoogleMap.addMarker(markerOptions);
        }


        if (mMarkerPoints.size() >= 2) {
            LatLng start = mMarkerPoints.get(0);
            LatLng destination = mMarkerPoints.get(1);
            String url = "https://maps.googleapis.com/maps/api/directions/json?" + "origin=" + start.latitude + "," + start.longitude + "&destination=" + destination.latitude + "," + destination.longitude + "&sensor=false";
//            Logger.e(" Direction url        " + url);
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        Constants.directionActivity = 0;

    }

    @Override
    protected void onPause() {
        super.onPause();
        Constants.directionActivity = 1;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.directionActivity = 1;
        unregisterReceiver(broadcastReceiver);
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

        if (apiName.equals("Arrived Trip")) {
            RideArrived rideArrived = new RideArrived();
            rideArrived = gson.fromJson(response, RideArrived.class);
            if (rideArrived.getResult().toString().equals("1")) {
                ll_pickup_location.setVisibility(View.GONE);
                ll_arrived.setVisibility(View.GONE);
                ll_drop_location.setVisibility(View.VISIBLE);
                ll_begin_trip.setVisibility(View.VISIBLE);
                Logger.e("result        " + rideArrived.getMsg().toString());

                mGoogleMap.setOnMyLocationChangeListener(null);

//                ll_timer.setVisibility(View.VISIBLE);
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (count < 60) {
//                                    tv_timer.setText(i + " : " + count);
//                                    count++;
//                                } else if ((count % 60) == 0) {
//                                    i++;
//                                    count = 0;
//                                    tv_timer.setText(i + " : " + count);
//                                    count++;
//
//                                    if (tv_timer.getText().toString().equals("1 : 0")) {
//                                        timer.cancel();
//                                        ll_cancel_by_driver.setVisibility(View.VISIBLE);
//                                        ll_timer.setVisibility(View.GONE);
//
//                                    }
//                                }
//                            }
//                        });
//                    }
//                }, 1000, 1000);
//
            } else {
                Logger.e("result        " + rideArrived.getMsg().toString());
            }
        }

        if (apiName.equals("Begin Trip")) {
//            RideArrived rideArrived = new RideArrived();
//            rideArrived = gson.fromJson(response, RideArrived.class);
//            if (rideArrived.getResult().toString().equals("1")) {
//                ll_begin_trip.setVisibility(View.GONE);
//                ll_end_trip.setVisibility(View.VISIBLE);
//                Log.e("result", rideArrived.getMsg().toString());
//
//                try {
//                    drawMarker(new LatLng(begin_lat, begin_long));
//                    drawMarker(new LatLng(Double.parseDouble(dropLat), Double.parseDouble(dropLong)));
//                    driverModule.rideTrackApi(rideId, driverId, begin_lat + "", begin_long + "", driver_token, language_id);
//                } catch (Exception e) {
//                    Logger.e("Exception in " + " begin lat marker ");
//                }
//                r = new Runnable() {
//                    @Override
//                    public void run() {
//                        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//                        if (mLastLocation != null) {
//                            double current_lat = mLastLocation.getLatitude();
//                            double current_long = mLastLocation.getLongitude();
//
//                            Logger.e("lat long from handler  " + current_lat + ", " + current_long);
//                            LatLng latLng = new LatLng(current_lat, current_long);
//
////                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).build();
////                            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                            driverModule.rideTrackApi(rideId, driverId, current_lat + "", current_long + "", driver_token, language_id);
//                        } else {
////                            Ignore This art
//                        }
//                        handler.postDelayed(r, 15000);
//                    }
//                };
//                Thread t = new Thread(r);
//                t.start();
//
//
//            } else {
//                Log.e("result", rideArrived.getMsg().toString());
//            }
        }

        if (apiName.equals("End Trip")) {
            RideArrived rideArrived = new RideArrived();
            rideArrived = gson.fromJson(response, RideArrived.class);

            if (rideArrived.getResult().toString().equals("1")) {

                handler.removeCallbacks(r);

                String amount = rideArrived.getDetails().getAmount();
                String distance = rideArrived.getDetails().getDistance();
                String time = rideArrived.getDetails().getTotTime();
                String ride_id = rideArrived.getDetails().getRideId();
                startActivity(new Intent(DirectionActivity.this, PriceFareActivity.class)
                        .putExtra("amount", amount)
                        .putExtra("distance", distance)
                        .putExtra("time", time)
                        .putExtra("customerId", customerId)
                        .putExtra("ride_id", ride_id));
                finish();
            } else {
                Log.e("result", rideArrived.getMsg().toString());
            }
        }

        if (apiName.equals("Cancel Ride")) {
            DeviceId deviceId;
            deviceId = gson.fromJson(response, DeviceId.class);
            if (deviceId.getResult().toString().equals("1")) {
                finish();
                startActivity(new Intent(DirectionActivity.this, RidesActivity.class));
            } else {
                Log.e("err", deviceId.getMsg().toString());
            }
        }

        if (apiName.equals("View Reasons")) {
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(response, ResultCheck.class);
            if (resultCheck.result.equals("1")) {
                cancelReasonCustomer = gson.fromJson(response, CancelReasonCustomer.class);
                showReasonDialog();
            } else {
                Toast.makeText(this, DirectionActivity.this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Update Ride Driver Location")) {
            UpdateLatLongBeforeArrived updateLatLongBeforeArrived;
            updateLatLongBeforeArrived = gson.fromJson(response, UpdateLatLongBeforeArrived.class);
            if (updateLatLongBeforeArrived.getResult().toString().equals("1")) {

                String latitude = updateLatLongBeforeArrived.getDetails().getDriverLat();
                String longitude = updateLatLongBeforeArrived.getDetails().getDriverLong();
                marker.remove();

//                try {
//                    marker = mGoogleMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
//                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_ola))
//                            .flat(true));
//                    CameraPosition cameraPosition = new CameraPosition.Builder()
//                            .target(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
//                            .zoom(16).build();
//                    mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                } catch (Exception e) {
//                    Logger.e("Exception to get track        " + e.toString());
//                }

                Log.e("Update ride lat long", updateLatLongBeforeArrived.getMsg().toString());
            } else {
                Log.e("Update ride lat long", updateLatLongBeforeArrived.getMsg().toString());
            }
        }

        if (apiName.equals("Track Ride")) {
            DeviceId deviceId;
            deviceId = gson.fromJson(response, DeviceId.class);
            if (deviceId.getResult().toString().equals("1")) {
                Log.e("Update ride lat long", deviceId.getMsg().toString());
            } else {
                Log.e("Update ride lat long", deviceId.getMsg().toString());
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    public void showReasonDialog() {

        final Dialog dialog = new Dialog(DirectionActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_for_cancel_reason);

        ListView lv_reasons = (ListView) dialog.findViewById(R.id.lv_reasons);
        lv_reasons.setAdapter(new ReasonAdapter(DirectionActivity.this, cancelReasonCustomer));

        lv_reasons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                reason_id = cancelReasonCustomer.getMsg().get(position).getReasonId();
                rideModule.cancelRideApi(rideId, reason_id, language_id);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mMarkerPoints = new ArrayList<>();
        MapsInitializer.initialize(this);

    }

    private class GetLocationAsyncStart extends AsyncTask<String, Void, String> {

        double x, y;
        StringBuilder str;

        public GetLocationAsyncStart(double latitude, double longitude) {
            x = latitude;
            y = longitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            str = new StringBuilder();
            try {
                geocoder = new Geocoder(DirectionActivity.this, Locale.ENGLISH);
                addresses = geocoder.getFromLocation(x, y, 1);
                if (geocoder.isPresent()) {
                    Address returnAddress = addresses.get(0);
                    String addressLine = returnAddress.getAddressLine(0);
                    String addressLine1 = returnAddress.getAddressLine(1);
                    String addressLine2 = returnAddress.getAddressLine(2);
                    str.append(addressLine + ", " + addressLine1 + ", " + addressLine2);
                } else {
                }
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }
            return str.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result.equals("")) {
                    new GetLocationAsyncStart(begin_lat, begin_long).execute();
                } else {
                    begin_location = result;
                }
            } catch (Exception e) {
                Log.e("err", e.toString());
            }
        }
    }

    private class GetLocationAsyncEnd extends AsyncTask<String, Void, String> {

        double x, y;
        StringBuilder str;

        public GetLocationAsyncEnd(double latitude, double longitude) {
            x = latitude;
            y = longitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            str = new StringBuilder();
            try {
                geocoder = new Geocoder(DirectionActivity.this, Locale.ENGLISH);
                addresses = geocoder.getFromLocation(x, y, 1);
                if (geocoder.isPresent()) {
                    Address returnAddress = addresses.get(0);
                    String addressLine = returnAddress.getAddressLine(0);
                    String addressLine1 = returnAddress.getAddressLine(1);
                    String addressLine2 = returnAddress.getAddressLine(2);
                    str.append(addressLine + ", " + addressLine1 + ", " + addressLine2);
                } else {
                }
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }
            return str.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result.equals("")) {
                    new GetLocationAsyncEnd(end_lat, end_long).execute();
                } else {
                    end_location = result;
                }
            } catch (Exception e) {
                Log.e("err", e.toString());
            }
        }
    }
}