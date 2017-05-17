package com.apporio.demotaxiappdriver.others;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.apporio.demotaxiappdriver.Config;
import com.apporio.demotaxiappdriver.LocationSession;
import com.apporio.demotaxiappdriver.manager.SessionManager;
import com.apporio.demotaxiappdriver.models.DriverLocation;
import com.apporio.demotaxiappdriver.models.register.Register;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by lenovo-pc on 3/31/2017.
 */

public class FirebaseUtils {


    FirebaseDatabase database;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference_geofire;
    SessionManager sessionmanager ;
    LocationSession applocation_manager;
    Context context ;
    GeoFire geoFire ;

    private String TAG = "FireBaseUtil";


    public FirebaseUtils(Context context){
        this.context = context ;
        database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference(Config.DriverReference);
        mDatabaseReference_geofire = database.getReference(Config.GeoFireReference);
        geoFire = new GeoFire(mDatabaseReference_geofire);
        applocation_manager  = new LocationSession(context);
        sessionmanager = new SessionManager(context);
    }


    public void setDriverOnlineStatus(boolean val){

        try{
            DriverLocation driverLocation ;
            if (val){
                driverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                        sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                        sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_login_logout),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT) ,applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG) ,""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT) , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));


                geoFire.setLocation(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), new GeoLocation(Double.parseDouble(applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)) +0.000001,Double.parseDouble(applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))), new GeoFire.CompletionListener() {
                    @Override
                    public void onComplete(String key, DatabaseError error) {
                        if (error != null) {
                            System.err.println("There was an error saving the location to GeoFire: " + error);
                        } else {
                            Log.d("##**##" , "Location saved successfully ");
                        }
                    }
                });


//            mDatabaseReference.child(""+sessionmanager.getCurrentRideDetails().get(SessionManager.KEY_DRIVER_ID)).child("driver_online_offline_status").setValue("1");
            }else {
                driverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                        sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                        sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_login_logout),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT) ,applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG) ,""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT) , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));

//            mDatabaseReference.child(""+sessionmanager.getCurrentRideDetails().get(SessionManager.KEY_DRIVER_ID)).child("driver_online_offline_status").setValue("2");
            }

            if(!sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID).equals("")){
                mDatabaseReference.child(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).setValue(driverLocation);
            }
        }catch (Exception e ){
            Log.e(""+TAG , ""+e.getMessage());
        }
    }


    public void setDriverCurrentLocation(String latitude , String longitude){


        DriverLocation driverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_login_logout),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),latitude ,longitude ,""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT) , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));

        mDatabaseReference.child(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).setValue(driverLocation);

    }



    public void setUpDriver ( ){



        final DriverLocation mDriverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_login_logout),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT) ,applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG) ,""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT) , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));


        mDatabaseReference.child(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).setValue(mDriverLocation);
    }


    public void logOutDriver(){
        DriverLocation driverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                "2",sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),"" ,"" ,"Location Text Need to be Send" , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));


        mDatabaseReference.child(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).setValue(driverLocation);
        setDriverOnlineStatus(false);
        sessionmanager.logoutUser();
    }






    public  void updateLocation_with_text(){

        Log.d("***updating lat to firebase " , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT));
        Log.d("***updating long to firebase " , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG));
        final DriverLocation mDriverLocation = new DriverLocation(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_NAME),sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_PHONE),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverEmail),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverImage),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverPassword),sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Device_id),getDeviceType(),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_rating),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CarTypeId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_ModelID),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CAR_Number),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_CityId),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_registeration_date),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_lisence),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_RC),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_insurence),"other_doc",""+sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdate),sessionmanager.getUserDetails().get(SessionManager.KEY_LastUpdateDate),sessionmanager.getUserDetails().get(SessionManager.KEY_Completed_Rides), sessionmanager.getUserDetails().get(SessionManager.KEY_Rejected_Rides),sessionmanager.getUserDetails().get(SessionManager.KEY_Cancelled_Rides),
                sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_login_logout),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Busy_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Detail_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_Admin_Status),sessionmanager.getUserDetails().get(SessionManager.KEY_CarTypeName),sessionmanager.getUserDetails().get(SessionManager.KEY_CarModelName),applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT) ,applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG) ,""+applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LOCATION_TEXT) , ""+ (System.currentTimeMillis()/1000) , ""+applocation_manager.getLocationDetails().get(LocationSession.KEY_BEARING_FACTOR));


        mDatabaseReference.child(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    DriverLocation driverLocation = dataSnapshot.getValue(DriverLocation.class);
                    Log.d("**##** driver_id" , ""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                    Log.d("**##** driver_online_offline" , ""+sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status));
                    Log.d("**##** driver_is_logged_in" , ""+sessionmanager.isLoggedIn());
                    Log.d("**##** driver_token" , ""+sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken));
                    Log.d("**##** driver_token from fire base " , ""+driverLocation.driver_token);
                    if(driverLocation.driver_token.equals(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DriverToken))){
                        if(sessionmanager.isLoggedIn() && sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_Online_Offline_Status).equals("1")){
                            // updating to main table of firebase
                            mDatabaseReference.child(sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).setValue(mDriverLocation);
                            // updating to geo firebase table
                            geoFire.setLocation(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID), new GeoLocation(Double.parseDouble(applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LAT)),Double.parseDouble(applocation_manager.getLocationDetails().get(LocationSession.KEY_CURRENT_LONG))), new GeoFire.CompletionListener() {
                                @Override
                                public void onComplete(String key, DatabaseError error) {
                                    if (error != null) {
                                        System.err.println("There was an error saving the location to GeoFire: " + error);
                                    }
                                }
                            });
                        }
                    }else{
                        // log out the driver
                        Toast.makeText(context, "Driver is logged in other device.", Toast.LENGTH_SHORT).show();
                        sessionmanager.logoutUser();
                    }
                }catch (Exception e){
                    Log.e("" +TAG, ""+e.getMessage());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void setDriverLoginLogoutStatus(boolean b) {
        if (b){
            mDatabaseReference.child(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).child("driver_login_logout").setValue("1");
        }else {
            mDatabaseReference.child(""+sessionmanager.getUserDetails().get(SessionManager.KEY_DRIVER_ID)).child("driver_login_logout").setValue("2");
        }
    }



    private String getDeviceType(){
        String devicetype = "";
        if(sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_flag).equals("1")){
            devicetype = "I-PHONE";
        }else if (sessionmanager.getUserDetails().get(SessionManager.KEY_Driver_flag).equals("2")){
            devicetype = "ANDROID";
        }
        return devicetype ;
    }



}
