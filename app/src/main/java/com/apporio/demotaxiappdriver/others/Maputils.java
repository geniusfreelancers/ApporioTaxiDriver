package com.apporio.demotaxiappdriver.others;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.util.Log;

import com.apporio.demotaxiappdriver.R;
import com.apporio.demotaxiappdriver.TrialLocationActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by lenovo-pc on 4/8/2017.
 */

public class Maputils {


    public static float getbearing(double in_lat , double in_long , double e_lat , double elong){
        Location startingLocation = new Location("starting point");
        startingLocation.setLatitude(in_lat);
        startingLocation.setLongitude(in_long);
        Location endingLocation = new Location("ending point");
        endingLocation.setLatitude(e_lat);
        endingLocation.setLongitude(elong);
        float targetBearing = startingLocation.bearingTo(endingLocation);
        return targetBearing ;
    }


    public static void moverCamera (GoogleMap googleMap , LatLng location){
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(17).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }





}
