package com.apporio.demotaxiappdriver;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.RideSession;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.models.newridesync.NewRideSync;
import com.apporio.demotaxiappdriver.models.rideaccept.RideAccept;

import com.apporio.demotaxiappdriver.models.viewrideinfodriver.ViewRideInfoDriver;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.MyBroadcastReceiver;
import com.apporio.demotaxiappdriver.parsing.RideModule;
import com.apporio.demotaxiappdriver.parsing.SyncModule;
import com.apporio.demotaxiappdriver.trackride.TrackRideActivity;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

public class ReceivePassengerActivity extends AppCompatActivity implements ApiFetcher {

    TextView tv_pickup_address, tv_timer, tv_drop_address;
    LinearLayout ll_accept, ll_reject;
    DonutProgress donutProgress;
    CounterClass timer;
    int j = 0;

    public static Activity receivepassengeractivity;
    SessionManager sessionManager;

    String rideId, driverId;

    ProgressDialog pd;

    RideModule rideModule;

    String driver_token;
    LanguageManager languageManager;
    String language_id;
    LinearLayout accept_reject_layout , ok_layout ;
    NewRideSync newRideSync ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_passenger);
        getSupportActionBar().hide();
        receivepassengeractivity = this;
        tv_pickup_address = (TextView) findViewById(R.id.tv_pickup_address);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        ll_accept = (LinearLayout) findViewById(R.id.ll_accept);
        ll_reject = (LinearLayout) findViewById(R.id.ll_reject);
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        accept_reject_layout = (LinearLayout) findViewById(R.id.accept_reject_layout);
        ok_layout = (LinearLayout) findViewById(R.id.ok_layout);
        ll_reject = (LinearLayout) findViewById(R.id.ll_reject);

        tv_drop_address = (TextView) findViewById(R.id.tv_drop_address);

        pd = new ProgressDialog(this);
        pd.setMessage(""+this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        sessionManager = new SessionManager(this);
        driverId = sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID);

        languageManager = new LanguageManager(this);
        language_id = languageManager.getLanguageDetail().get(LanguageManager.LANGUAGE_ID);

        driver_token = sessionManager.getUserDetails().get(SessionManager.KEY_DriverToken);

        rideId = super.getIntent().getExtras().getString("rideId");
        rideModule = new RideModule(ReceivePassengerActivity.this);
        rideModule.viewRideInfoApi(rideId, driver_token, language_id);

        SyncModule syncModule = new SyncModule(this);
        syncModule.newRideSyncApi(rideId, sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), language_id);


        tv_timer.setText("00:20");
        timer = new CounterClass(20000, 1000);
        donutProgress.setProgress(j);
        donutProgress.setTextColor(Color.parseColor("#ffffff"));
        donutProgress.setFinishedStrokeColor(Color.parseColor("#000000"));
        donutProgress.setFinishedStrokeWidth(5);
        donutProgress.setUnfinishedStrokeColor(Color.parseColor("#e9e9e9"));
        donutProgress.setMax(20);

        ll_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    MyBroadcastReceiver.mediaPlayer.stop();
                } catch (Exception e) {
                    Logger.e("Exception in stop Tone        " + e.toString());
                }

                rideModule.acceptRideApi(rideId, driverId, driver_token, language_id);
                timer.cancel();
            }
        });

        ll_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    MyBroadcastReceiver.mediaPlayer.stop();
                } catch (Exception e) {
                    Logger.e("Exception in stop Tone        " + e.toString());
                }

                rideModule.rejectRideApi(rideId, driverId, driver_token, language_id);
                timer.cancel();
            }
        });


        findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReceivePassengerActivity.this , MainActivity.class));
            }
        });
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

        if (apiName.equals("View Ride Info")) {

            Log.d("*****view_ride_info " , ""+response);
            ViewRideInfoDriver viewRideInfoDriver;
            viewRideInfoDriver = gson.fromJson(response, ViewRideInfoDriver.class);

            if (viewRideInfoDriver.getResult().toString().equals("1")) {
                String pickupAddress = viewRideInfoDriver.getDetails().getPickupLocation();
                String dropAddress = viewRideInfoDriver.getDetails().getDropLocation();
                tv_pickup_address.setText(pickupAddress);
                tv_drop_address.setText(dropAddress);

                timer.start();
            } else if (viewRideInfoDriver.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + viewRideInfoDriver.getMsg());
            } else {
                Toast.makeText(ReceivePassengerActivity.this, ""+this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Accept Ride")) {
            RideAccept rideAccept;
            rideAccept = gson.fromJson(response, RideAccept.class);
            Log.d("*****accept_ride " , ""+response);
            if (rideAccept.getResult()== 1) {
                new RideSession(this).setRideSesion(rideAccept);
                startActivity(new Intent(this, TrackRideActivity.class)
                        .putExtra("customer_name" , ""+rideAccept.getDetails().getUser_name())
                        .putExtra("customer_phone" ,""+rideAccept.getDetails().getUser_phone()));
                finish();
            } else if (rideAccept.getResult() == 419) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + rideAccept.getMsg());
            } else {
                Toast.makeText(this, ReceivePassengerActivity.this.getResources().getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
            }
        }

        if (apiName.equals("Reject Ride")) {
            Log.d("*****reject_ride " , ""+response);
            DeviceId deviceId;
            deviceId = gson.fromJson(response, DeviceId.class);
            if (deviceId.getResult().toString().equals("1")) {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
                ReceivePassengerActivity.receivepassengeractivity.finish();
            } else if (deviceId.getResult().toString().equals("419")) {
                sessionManager.logoutUser();
                Intent intent = new Intent(this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
                Logger.e("lat long update       " + deviceId.getMsg());
            } else {
                Toast.makeText(this, "" + deviceId.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
        if (apiName.equals("New Ride Sync")) {

            newRideSync = new NewRideSync();
            newRideSync = gson.fromJson(response, NewRideSync.class);
            Log.d("*****new ride sync  " , ""+response);

            if (newRideSync.getResult().toString().equals("1")) {
                // make view visible
                accept_reject_layout.setVisibility(View.VISIBLE);
                ok_layout.setVisibility(View.GONE);
            }else if (newRideSync.getResult().toString().equals("0")){
                // ride is expired make those view invisible
                accept_reject_layout.setVisibility(View.GONE);
                ok_layout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            tv_timer.setText(hms);
            j++;
            donutProgress.setProgress(j);
        }

        @Override
        public void onFinish() {
            tv_timer.setText(ReceivePassengerActivity.this.getResources().getString(R.string.time_out));
            j++;

            try {
                MyBroadcastReceiver.mediaPlayer.stop();
            } catch (Exception e) {
                Logger.e("Exception in stop Tone        " + e.toString());
            }

            donutProgress.setProgress(j);
            if(newRideSync.getResult() == 1 ){
                rideModule.rejectRideApi(rideId, driverId, driver_token, language_id);
            }
        }
    }
}
