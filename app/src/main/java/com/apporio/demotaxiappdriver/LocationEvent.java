package com.apporio.demotaxiappdriver;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by lenovo-pc on 4/28/2017.
 */

public class LocationEvent {
    private String latitude;
    private String longitude;
    private String full_address;
    private float bearing_factor ;
    private double meter_value ;
    private boolean is_meter_value_cleared ;
    private float current_speed ;
    private long current_time ;

    public long getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(long current_time) {
        this.current_time = current_time;
    }



    public float getCurrent_speed() {
        return current_speed;
    }

    public void setCurrent_speed(float current_speed) {
        this.current_speed = current_speed;
    }






    public boolean is_meter_value_cleared() {
        return is_meter_value_cleared;
    }

    public void setIs_meter_value_cleared(boolean is_meter_value_cleared) {
        this.is_meter_value_cleared = is_meter_value_cleared;
    }

    public double getMeter_value() {
        return meter_value;
    }

    public void setMeter_value(double meter_value) {
        this.meter_value = meter_value;
    }



    public String getlatitude_string() {
        return latitude;
    }
    public String getLongitude_string() {
        return longitude;
    }
    public String getFullAddress() {
        return full_address;
    }
    public float getBearingfactor() {
        return bearing_factor;
    }

    public void setLocationWithAddress(String latitude_string , String longitude_string , String full_address , float bearing_factor) {
        this.latitude = latitude_string;
        this.longitude = longitude_string;
        this.full_address = full_address;
        this.bearing_factor = bearing_factor ;
    }
}
