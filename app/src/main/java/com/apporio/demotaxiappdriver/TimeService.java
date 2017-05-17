package com.apporio.demotaxiappdriver;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.manager.LanguageManager;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.deviceid.DeviceId;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.others.FirebaseUtils;
import com.apporio.demotaxiappdriver.others.Maputils;
import com.apporio.demotaxiappdriver.parsing.DriverModule;
import com.entire.sammalik.samlocationandgeocoding.SamLocationRequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lenovo-pc on 4/28/2017.
 */

public class TimeService extends Service implements ApiFetcher{

    SamLocationRequestService sam_location ;
    LocationSession app_location_mamanger ;
    FirebaseUtils firebaseUtils ;
    DriverModule driverModule ;
    SessionManager sessionManager ;
    LanguageManager languageManager ;

    // constant

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        app_location_mamanger = new LocationSession(this);
        sam_location = new SamLocationRequestService(this);
        firebaseUtils = new FirebaseUtils(this);
        driverModule = new DriverModule(this);
        sessionManager = new SessionManager(this);
        languageManager = new LanguageManager(this);




        // cancel if already existed
        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, Config.Location_Updation_Interval);
    }



    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if(!sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID).equals("")  && sessionManager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status).equals("1")){
                        updateLocation();
                    }
                }

            });
        }

        private void updateLocation() {

            sam_location.executeService(new SamLocationRequestService.SamLocationListener() {
                @Override
                public void onLocationUpdate(Location location, Address address) {

                    try{
                        if(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT).equals("")){
                        //// fill the location manager for first time and update on firebase
                            updateLocationToSession(location , address);
                            Log.d("*****" , "updating location first time ");
                        }else{
                            Log.d("*** getting accuracy " , ""+location.getAccuracy());
                            if(AerialDistance.aerialDistanceFunctionInMeters(Double.parseDouble(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)),Double.parseDouble(app_location_mamanger.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG)), location.getLatitude(),location.getLongitude()) > location.getAccuracy()){
                                // if distance between two lat long is greater than 50 then only update on firebase and location session
                                updateLocationToSession(location , address);
                            }
                        }
//                        Toast.makeText(TimeService.this, "speed => "+location.getSpeed()+"  time => "+location.getTime(), Toast.LENGTH_SHORT).show();
                        firebaseUtils.updateLocation_with_text();
                    }catch (Exception e){
                        app_location_mamanger.setLocationAddress(null);
                        Log.e("******" , "Exception occur while updating location to fire base => "+e.getMessage() );
                    }
//                    LocationEvent location_event = new LocationEvent();
//                    location_event.setCurrent_speed(location.getSpeed());
//                    location_event.setCurrent_time(location.getTime());
//                    EventBus.getDefault().post(location_event);
                }
            });
        }

        private void updateLocationToSession(Location location, Address address) {
            if(location.getBearing() != 0.0){
                app_location_mamanger.setBearingFactor(""+location.getBearing());
            }
            app_location_mamanger.setLocationLatLong(""+location.getLatitude() , ""+location.getLongitude());
            app_location_mamanger.setLocationAddress(""+address.getAddressLine(0)+", "+address.getAddressLine(1)+", "+address.getAddressLine(2)+", "+address.getAddressLine(3));
        }


    }



    @Override
    public void onAPIRunningState(int a) {

    }

    @Override
    public void onFetchProgress(int progress) {

    }

    @Override
    public void onFetchComplete(String response, String apiName) {
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        DeviceId deviceIdnew;
//        deviceIdnew = gson.fromJson(response, DeviceId.class);
//
//        if (deviceIdnew.getResult().toString().equals("419")) {
//            sessionManager.logoutUser();
//            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//
//            Logger.e("lat long update       " + deviceIdnew.getMsg());
//        }
    }

    @Override
    public void onFetchFailed(ANError error) {

    }

    @Override
    public void WhichApi(String apiName) {

    }

}