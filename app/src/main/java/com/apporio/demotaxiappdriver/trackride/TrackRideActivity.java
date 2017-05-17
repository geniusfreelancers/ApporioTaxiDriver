package com.apporio.demotaxiappdriver.trackride;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.apporio.demotaxiappdriver.AerialDistance;
import com.apporio.demotaxiappdriver.ApiManager;
import com.apporio.demotaxiappdriver.Config;
import com.apporio.demotaxiappdriver.DirectionActivity;
import com.apporio.demotaxiappdriver.LocationEvent;
import com.apporio.demotaxiappdriver.LocationSession;
import com.apporio.demotaxiappdriver.MeterEvent;
import com.apporio.demotaxiappdriver.PriceFareActivity;
import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.RidesActivity;
import com.apporio.demotaxiappdriver.adapter.ReasonAdapter;
import com.apporio.demotaxiappdriver.locationaltrackerclasses.LocationManager;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.RideSession;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.ResultCheck;
import com.apporio.demotaxiappdriver.models.cancelreasoncustomer.CancelReasonCustomer;
import com.apporio.demotaxiappdriver.models.ridearrived.RideArrived;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;

public class TrackRideActivity extends AppCompatActivity implements OnMapReadyCallback , ApiManager.APIFETCHER{


    GoogleMap mGooglemap;
    LocationSession locationSession;
    LanguageManager languageManager ;
    RideSession rideSession;
    SessionManager sessionManager ;
    TextView customer_info_txt, drop_location_txt, trip_status_txt, your_location_txt, meter_txt , cancel_btn ,customer_phone_txt;
    LinearLayout root  ;
    ApiManager apiManager ;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager(this);
        super.onCreate(savedInstanceState);
        locationSession = new LocationSession(this);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);
        rideSession = new RideSession(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(""+this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        setContentView(R.layout.activity_track_ride);
        customer_info_txt = (TextView) findViewById(R.id.customer_info_txt);
        drop_location_txt = (TextView) findViewById(R.id.drop_location_txt);
        trip_status_txt = (TextView) findViewById(R.id.trip_status_txt);
        your_location_txt = (TextView) findViewById(R.id.your_location_txt);
        cancel_btn = (TextView) findViewById(R.id.cancel_btn);
        meter_txt = (TextView) findViewById(R.id.meter_txt);
        customer_phone_txt = (TextView) findViewById(R.id.customer_phone_txt);
        root = (LinearLayout) findViewById(R.id.root);


        getSupportActionBar().hide();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+getIntent().getExtras().getString("customer_phone")));
                if (ActivityCompat.checkSelfPermission(TrackRideActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        trip_status_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("3")){
                    // run arrived api
                    apiManager.execution_method_get(Config.ApiKeys.KEY_ARRIVED , Apis.arrivedTrip+"?ride_id="+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID)+"&driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }else if (rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("5")){
                    // run begin trip api
                    apiManager.execution_method_get(Config.ApiKeys.KEY_BEGIN_TRIP , Apis.beginTrip+"?ride_id="+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID)+"&driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&begin_lat="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)+"&begin_long="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE)+"&begin_location="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LOCATION)+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }else if (rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")){
                    // run ride end api
                    apiManager.execution_method_get(Config.ApiKeys.KEY_END_TRIP , Apis.endTripMeter+"?ride_id="+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID)+"&driver_id="+sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)+"&begin_lat="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)+"&begin_long="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE)+"&begin_location="+rideSession.getCurrentRideDetails().get(RideSession.PICK_LOCATION)+"&end_lat="+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)+"&end_long="+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)+"&end_location="+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT)+"&end_time="+getArrivalTime()+"&distance="+locationSession.getLocationDetails().get(LocationSession.KEY_METER_VALUE)+"&driver_token="+sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken)+"&language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
                }
            }
        });

        findViewById(R.id.navigation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("3")){
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" + locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)+","+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)+"&daddr=" + rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)+","+rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE)));
                    startActivity(intent);
                }else if (rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")){
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" + locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)+","+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)+"&daddr=" + rideSession.getCurrentRideDetails().get(RideSession.DROP_LATITUDE)+","+rideSession.getCurrentRideDetails().get(RideSession.DROP_LONGITUDE)));
                    startActivity(intent);
                }else {
                    Snackbar.make(root , ""+TrackRideActivity.this.getResources().getString(R.string.please_start_your_ridr_first) , Snackbar.LENGTH_SHORT).show();
                }

            }
        });



        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiManager.execution_method_get(Config.ApiKeys.KEY_CANCEL_REASONS , Apis.cancelReason+"?language_id="+languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID));
            }
        });
    }

    private void setView() {
        customer_info_txt.setText("" + getIntent().getExtras().getString("customer_name"));
        customer_phone_txt.setText(""+getIntent().getExtras().getString("customer_phone"));
        drop_location_txt.setText("" + rideSession.getCurrentRideDetails().get(RideSession.DROP_LOCATION));
        setviewAccordingToStatus();
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGooglemap = googleMap;

        try {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.midnight_commander_theme));
        } catch (Resources.NotFoundException e) {
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         return;
        }
        mGooglemap.setMyLocationEnabled(true);
        if(!locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT).equals("")){
            your_location_txt.setText(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
        }

        setView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationEvent event){
        if(event.getFullAddress().equals("null")  || event.getFullAddress()==null){
            your_location_txt.setText(""+locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT));
        }
        your_location_txt.setText(""+event.getFullAddress());
        meter_txt.setText(""+ AerialDistance.meterToKM(event.getMeter_value())+ " KM");
        if(event.is_meter_value_cleared()){
            drawRoute(new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE))) ,  new LatLng(Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))) ,mGooglemap ,R.drawable.ic_contact_green , R.drawable.ic_very_small );
        }else {
//            Toast.makeText(this, "animate Icon", Toast.LENGTH_SHORT).show();
        }
    }



    public void setviewAccordingToStatus (){
        if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("3")){
            ////  set view when driver needs to reach over pick up point
            trip_status_txt.setText(""+this.getResources().getString(R.string.arrived));
            drawRoute(new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE))) ,  new LatLng(Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) , Double.parseDouble(locationSession.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))) ,mGooglemap ,R.drawable.ic_contact_green , R.drawable.ic_very_small );
            cancel_btn.setVisibility(View.VISIBLE);
        }if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("5")){
            trip_status_txt.setText(""+this.getResources().getString(R.string.begin));
            cancel_btn.setVisibility(View.VISIBLE);
            drawRoute(new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE))) , new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.DROP_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.DROP_LONGITUDE))),mGooglemap , R.drawable.dot_green , R.drawable.dot_red);
        }if(rideSession.getCurrentRideDetails().get(RideSession.RIDE_STATUS).equals("6")){
            drawRoute(new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.PICK_LONGITUDE))) , new LatLng(Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.DROP_LATITUDE)) , Double.parseDouble(rideSession.getCurrentRideDetails().get(RideSession.DROP_LONGITUDE))),mGooglemap , R.drawable.dot_green , R.drawable.dot_red);
            trip_status_txt.setText(""+this.getResources().getString(R.string.end));
            cancel_btn.setVisibility(View.GONE);
        }

    }






    public void drawRoute (LatLng origin  , LatLng destination , GoogleMap mMap , int origin_icon  , int destination_icon ){
        mGooglemap.clear();
        DrawRouteMaps.getInstance(this).draw(origin, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, origin, origin_icon, ""+rideSession.getCurrentRideDetails().get(RideSession.PICK_LOCATION));
        DrawMarker.getInstance(this).draw(mMap, destination, destination_icon, ""+rideSession.getCurrentRideDetails().get(RideSession.DROP_LOCATION));

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 500, 30));
    }



    public String getArrivalTime (){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        String h = hour + ":" + minutes + ":" + sec;
        String[] h1 = h.split(":");
        int hours = Integer.parseInt(h1[0]);
        int minute = Integer.parseInt(h1[1]);
        int second = Integer.parseInt(h1[2]);
        return ""+(second + (60 * minute) + (3600 * hours));
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if(a == ApiManager.APIFETCHER.KEY_API_IS_STARTED){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ResultCheck result_check = gson.fromJson(""+script, ResultCheck.class);

        if(result_check.result.equals("1")){
            if(APINAME.equals(Config.ApiKeys.KEY_ARRIVED)){
                rideSession.setRideStatus("5");
            }if(APINAME.equals(Config.ApiKeys.KEY_BEGIN_TRIP)){
                rideSession.setRideStatus("6");
                locationSession.clearMeterValue();
            }if(APINAME.equals(Config.ApiKeys.KEY_END_TRIP)){
                rideSession.setRideStatus("7");

                RideArrived rideArrived = new RideArrived();
                rideArrived = gson.fromJson(""+script, RideArrived.class);


                startActivity(new Intent(this, PriceFareActivity.class)
                        .putExtra("amount", rideArrived.getDetails().getAmount())
                        .putExtra("distance", rideArrived.getDetails().getDistance())
                        .putExtra("time", rideArrived.getDetails().getTotTime())
                        .putExtra("customerId", rideSession.getCurrentRideDetails().get(RideSession.USER_ID))
                        .putExtra("ride_id", rideArrived.getDetails().getRideId())
                        .putExtra("done_ride_id" , rideArrived.getDetails().getDoneRideId()));
                finish();
                rideSession.clearRideSession();
            }if(APINAME.equals(Config.ApiKeys.KEY_CANCEL_REASONS)){
                CancelReasonCustomer cancelReasonCustomer = gson.fromJson(""+script, CancelReasonCustomer.class);
                showReasonDialog(cancelReasonCustomer);
            }if(APINAME.equals(Config.ApiKeys.KEY_CANCEL_TRIP)){
                finish();
                startActivity(new Intent(this, RidesActivity.class));
            }
            setviewAccordingToStatus();

        }else {
            Toast.makeText(this, "Server Error while executing API ", Toast.LENGTH_SHORT).show();
        }
        Log.d("*****"+APINAME , ""+script);


    }














 /////////////////// dialog
 public void showReasonDialog(final CancelReasonCustomer cancelReasonCustomer) {

     final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     Window window = dialog.getWindow();
     dialog.setCancelable(true);
     window.setGravity(Gravity.CENTER);
     window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     dialog.setContentView(R.layout.dialog_for_cancel_reason);

     ListView lv_reasons = (ListView) dialog.findViewById(R.id.lv_reasons);
     lv_reasons.setAdapter(new ReasonAdapter(this, cancelReasonCustomer));

     lv_reasons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             apiManager.execution_method_get(Config.ApiKeys.KEY_CANCEL_TRIP , Apis.cancelRide+"?ride_id="+rideSession.getCurrentRideDetails().get(RideSession.RIDE_ID)+"&reason_id="+cancelReasonCustomer.getMsg().get(position).getReasonId());
             dialog.dismiss();
         }
     });
     dialog.show();
 }





}
