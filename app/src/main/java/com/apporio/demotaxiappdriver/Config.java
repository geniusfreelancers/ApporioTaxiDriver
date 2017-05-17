package com.apporio.demotaxiappdriver;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.apporio.demotaxiappdriver.models.ConfigModelResponse;

/**
 * Created by lenovo-pc on 4/1/2017.
 */

public class Config {

    public static ConfigModelResponse app_config;
    public static String DriverReference = "Drivers_A";
    public static String Devicetype = "2";
    public static String GeoFireReference = "geofire";

    public static float bearingfactor = 0 ;



    public interface ApiKeys{
        String KEY_ARRIVED = "ride_arrived";
        String KEY_BEGIN_TRIP = "begin_trip";
        String KEY_END_TRIP = "end_trip";
        String KEY_CANCEL_TRIP = "cancel_trip";
        String KEY_CANCEL_REASONS = "cancel_reasons";
        String KEY_View_cities = "view_cities";
        String KEY_View_car_by_city = "view_car_by_cities";
        String KEY_View_car_Model = "view_car_model";
        String KEY_Driver_register = "driver_register";
        String KEY_View_done_ride_info = "view_done_ride_info";


    }


//////////////////////////// config attributes

    public static final long Location_Updation_Interval = 5 * 1000; // 10 seconds
    public static int DistanceGap_tail = 50 ; //










}
