package com.apporio.demotaxiappdriver.locationwork;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import com.apporio.demotaxiappdriver.logger.Logger;
import com.google.android.gms.maps.GoogleMap;

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;
    GoogleMap mmp;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    Location location;
    double latitude;
    double longitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    protected LocationManager locationManager;

    public GPSTracker(Context context, GoogleMap mMap) {
        this.mContext = context;
        this.mmp = mMap;
        getLocation();
    }

    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {
//                Logger.e("gps & network" + " is not Present");
            } else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
//                    Logger.e("network is" + " Enabled");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    Logger.e("location manager in network  " + locationManager);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                        Logger.e("location in network   " + location);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
//                            Logger.e("Lat Long in network       " + latitude + ", " + longitude);
                        } else {
//                            Logger.e("Location " + " is null in network condition");
                        }
                    } else {
//                        Logger.e("Location Manger " + " is null in network condition");
                    }
                } else if (isGPSEnabled) {
//                    Logger.e("Gps is" + " Enabled");
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    Logger.e("location manager in gps  " + locationManager);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        Logger.e("location   " + location);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
//                            Logger.e("Lat Long in Gps tracker       " + latitude + ", " + longitude);
                        } else {
//                            Logger.e("Location " + " is null in gps condition");
                        }
                    } else {
//                        Logger.e("Location Manger " + " is null in gps condition");
                    }
                }
            }
        } catch (Exception e) {
            Logger.e("Exception in " + "get location method");
        }
        return location;
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    /**
     * Function to check if best network provider
     *
     * @return boolean
     */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    /**
     * Function to show settings alert dialog
     */

    public void showSettingsAlert() {
        try {
            AlertDialog.Builder alertDialog = null;

            alertDialog = new AlertDialog.Builder(mContext);

            // Setting Dialog Title
            alertDialog.setTitle("GPS is settings");

            // Setting Dialog Message
            alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

            // Setting Icon to Dialog
            //alertDialog.setIcon(R.drawable.delete);

            // On pressing Settings button
            alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mContext.startActivity(intent);
                    //SplashActivity.splashActivity.finish();
                }
            });

            // on pressing cancel button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    // SplashActivity.splashActivity.finish();
                }
            });
            // Showing Alert Message
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */
    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

//        if(now != null){
//            now.remove();
//        }

      /*  double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
        now=mmp.addMarker(new MarkerOptions().title("i am here").
                position(new LatLng(GPSTracker.this.getLatitude(),
                        GPSTracker.this.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.drawable.rszimage)));
        mmp.setMyLocationEnabled(true);
        mmp.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mmp.animateCamera(CameraUpdateFactory.zoomTo(15));*/
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}