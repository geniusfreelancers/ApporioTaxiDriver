package com.apporio.demotaxiappdriver.models;

/**
 * Created by bhuvneshapporio on 3/10/2017.
 */

public class TrackRideMeter {

    public String driver_id;
    public String driver_travel_distance ;

    public TrackRideMeter() {
    }

    public TrackRideMeter(String driver_id , String driver_travel_distance ) {
        this.driver_id = driver_id;
        this.driver_travel_distance = driver_travel_distance;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }


}
