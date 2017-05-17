package com.apporio.demotaxiappdriver.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.apporio.demotaxiappdriver.models.rideaccept.RideAccept;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by lenovo-pc on 4/23/2017.
 */

public class RideSession {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ridePrefrences";
    public static final String RIDE_ID = "ride_id";
    public static final String USER_ID = "user_id";
    public static final String COUPON_CODE = "coupon_code";
    public static final String PICK_LATITUDE = "pick_lat";
    public static final String PICK_LONGITUDE = "pick_long";
    public static final String PICK_LOCATION = "pick_location";
    public static final String DROP_LATITUDE = "drop_lat";
    public static final String DROP_LONGITUDE = "drop_long";
    public static final String DROP_LOCATION = "drop_location";
    public static final String RIDE_DATE = "ride_date";
    public static final String RIDE_TIME = "ride_time";
    public static final String RIDE_LATER_DATE = "later_date";
    public static final String RIDE_LATER_TIME = "later_time";
    public static final String DRIVER_ID = "driver_id";
    public static final String RIDE_TYPE = "ride_type";
    public static final String RIDE_STATUS = "ride_status";
    public static final String STATUS = "status";
    public static final String IS_RIDE_ONGOING = "ride_ongoing";



    public RideSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setRideSesion( RideAccept rideAccept ) {
        editor.putBoolean(IS_RIDE_ONGOING, true);
        editor.putString(RIDE_ID, rideAccept.getDetails().getRide_id());
        editor.putString(USER_ID, rideAccept.getDetails().getUser_id());
        editor.putString(COUPON_CODE, rideAccept.getDetails().getCoupon_code());
        editor.putString(PICK_LATITUDE, rideAccept.getDetails().getPickup_lat());
        editor.putString(PICK_LONGITUDE, rideAccept.getDetails().getPickup_long());
        editor.putString(PICK_LOCATION, rideAccept.getDetails().getPickup_location());
        editor.putString(DROP_LATITUDE, rideAccept.getDetails().getDrop_lat());
        editor.putString(DROP_LONGITUDE, rideAccept.getDetails().getDrop_long());
        editor.putString(DROP_LOCATION, rideAccept.getDetails().getDrop_location());
        editor.putString(RIDE_DATE, rideAccept.getDetails().getRide_date());
        editor.putString(RIDE_TIME, rideAccept.getDetails().getRide_time());
        editor.putString(RIDE_LATER_DATE, rideAccept.getDetails().getLater_date());
        editor.putString(RIDE_LATER_TIME, rideAccept.getDetails().getLater_time());
        editor.putString(DRIVER_ID, rideAccept.getDetails().getDriver_id());
        editor.putString(RIDE_TYPE, rideAccept.getDetails().getRide_type());
        editor.putString(RIDE_STATUS, rideAccept.getDetails().getRide_status());
        editor.putString(STATUS, rideAccept.getDetails().getStatus());
        editor.commit();
    }





    public HashMap<String, String> getCurrentRideDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(RIDE_ID , pref.getString(RIDE_ID , ""));
        user.put(USER_ID , pref.getString(USER_ID , ""));
        user.put(COUPON_CODE , pref.getString(COUPON_CODE , ""));
        user.put(PICK_LATITUDE , pref.getString(PICK_LATITUDE , ""));
        user.put(PICK_LONGITUDE , pref.getString(PICK_LONGITUDE , ""));
        user.put(PICK_LOCATION , pref.getString(PICK_LOCATION , ""));
        user.put(DROP_LATITUDE , pref.getString(DROP_LATITUDE , ""));
        user.put(DROP_LONGITUDE, pref.getString(DROP_LONGITUDE, ""));
        user.put(DROP_LOCATION , pref.getString(DROP_LOCATION , ""));
        user.put(RIDE_DATE , pref.getString(RIDE_DATE , ""));
        user.put(RIDE_TIME , pref.getString(RIDE_TIME , ""));
        user.put(RIDE_LATER_DATE, pref.getString(RIDE_LATER_DATE, ""));
        user.put(RIDE_LATER_TIME , pref.getString(RIDE_LATER_TIME , ""));
        user.put(DRIVER_ID , pref.getString(DRIVER_ID , ""));
        user.put(RIDE_TYPE , pref.getString(RIDE_TYPE , ""));
        user.put(RIDE_STATUS , pref.getString(RIDE_STATUS , ""));
        user.put(STATUS , pref.getString(STATUS , ""));
        return user;
    }




    public boolean isRideonGoing() {
        return pref.getBoolean(IS_RIDE_ONGOING, false);
    }


    public void setRideStatus (String ride_status ){
        editor.putString(RIDE_STATUS, ride_status);
        editor.commit();
    }

    public void clearRideSession(){
        editor.clear();
        editor.commit();
    }



}
