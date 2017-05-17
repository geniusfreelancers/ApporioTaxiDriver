package com.apporio.demotaxiappdriver.manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LoginPrefrences";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_DRIVER_ID = "driver_id";
    public static final String KEY_DRIVER_NAME = "driver_name";
    public static final String KEY_DRIVER_PHONE = "driver_phone_number";
    public static final String KEY_DriverEmail = "driver_email";
    public static final String KEY_DriverImage = "driver_image";
    public static final String KEY_DriverPassword = "driver_password";
    public static final String KEY_DriverToken = "driver_token";
    public static final String KEY_Driver_Device_id= "driver_device_id";
    public static final String KEY_Driver_flag = "driver_flag";
    public static final String KEY_Driver_rating = "driver_rating";
    public static final String KEY_Driver_CarTypeId = "driver_car_type_id";
    public static final String KEY_Driver_ModelID = "driver_model_id";
    public static final String KEY_Driver_CAR_Number = "driver_car_number";
    public static final String KEY_Driver_CityId = "driver_city_id";
    public static final String KEY_Driver_registeration_date = "drievr_registration_date";
    public static final String KEY_Driver_lisence = "driver_lisence";
    public static final String KEY_Driver_RC = "driver_rc";
    public static final String KEY_Driver_insurence = "driver_insurance";
    public static final String KEY_Driver_Other_DOC = "otherdoc";
    public static final String KEY_LastUpdate = "last_update";
    public static final String KEY_LastUpdateDate = "last_update_date";
    public static final String KEY_Completed_Rides = "completed_rides";
    public static final String KEY_Rejected_Rides  = "Rejected_Rides";
    public static final String KEY_Cancelled_Rides = "cancelled_rides";
    public static final String KEY_Driver_login_logout = "login_logout";
    public static final String KEY_Driver_Busy_Status = "busy_status";
    public static final String KEY_Driver_Online_Offline_Status = "offline_online_status";
    public static final String KEY_Detail_Status = "details_status";
    public static final String KEY_Admin_Status  = "admin_status";
    public static final String KEY_CarTypeName = "car_type_name";
    public static final String KEY_CarModelName = "car_model_name";



    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String DriverId ,
            String DriverName ,
            String DriverPhoneNumber ,
            String DriverEmail ,
            String DriverImage ,
            String DriverPassword ,
            String DriverToken ,
            String Driver_Device_id,
            String Driver_flag ,
            String Driver_rating ,
            String Driver_CarTypeId ,
            String Driver_ModelID ,
            String Driver_CAR_Number ,
            String Driver_CityId ,
            String Driver_registeration_date ,
            String Driver_lisence ,
            String Driver_RC ,
            String Driver_insurence ,
            String Driver_Other_DOC ,
            String LastUpdate ,
            String LastUpdateDate ,
            String Completed_Rides ,
            String Rejected_Rides  ,
            String Cancelled_Rides ,
            String Driver_login_logout ,
            String Driver_Busy_Status ,
            String Driver_Online_Offline_Status ,
            String Detail_Status ,
            String Admin_Status  ,
            String CarTypeName ,
            String CarModelName ) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_DRIVER_ID, DriverId);
        editor.putString(KEY_DRIVER_NAME, DriverName);
        editor.putString(KEY_DRIVER_PHONE, DriverPhoneNumber);
        editor.putString(KEY_DriverEmail, DriverEmail);
        editor.putString(KEY_DriverImage, DriverImage);
        editor.putString(KEY_DriverPassword, DriverPassword);
        editor.putString(KEY_DriverToken, DriverToken);
        editor.putString(KEY_Driver_Device_id, Driver_Device_id);
        editor.putString(KEY_Driver_flag, Driver_flag);
        editor.putString(KEY_Driver_rating, Driver_rating);
        editor.putString(KEY_Driver_CarTypeId, Driver_CarTypeId);
        editor.putString(KEY_Driver_ModelID, Driver_ModelID);
        editor.putString(KEY_Driver_CAR_Number, Driver_CAR_Number);
        editor.putString(KEY_Driver_CityId, Driver_CityId);
        editor.putString(KEY_Driver_registeration_date, Driver_registeration_date);
        editor.putString(KEY_Driver_lisence, Driver_lisence);
        editor.putString(KEY_Driver_RC, Driver_RC);
        editor.putString(KEY_Driver_insurence, Driver_insurence);
        editor.putString(KEY_Driver_Other_DOC, Driver_Other_DOC);
        editor.putString(KEY_LastUpdate, LastUpdate);
        editor.putString(KEY_LastUpdateDate, LastUpdateDate);
        editor.putString(KEY_Completed_Rides, Completed_Rides);
        editor.putString(KEY_Rejected_Rides, Rejected_Rides);
        editor.putString(KEY_Cancelled_Rides, Cancelled_Rides);
        editor.putString(KEY_Driver_login_logout, Driver_login_logout);
        editor.putString(KEY_Driver_Busy_Status, Driver_Busy_Status);
        editor.putString(KEY_Driver_Online_Offline_Status, Driver_Online_Offline_Status);
        editor.putString(KEY_Detail_Status, Detail_Status);
        editor.putString(KEY_Admin_Status, Admin_Status);
        editor.putString(KEY_CarTypeName, CarTypeName);
        editor.putString(KEY_CarModelName, CarModelName);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_DRIVER_ID , pref.getString(KEY_DRIVER_ID , ""));
        user.put(KEY_DRIVER_NAME , pref.getString(KEY_DRIVER_NAME , ""));
        user.put(KEY_DRIVER_PHONE , pref.getString(KEY_DRIVER_PHONE , ""));
        user.put(KEY_DriverEmail , pref.getString(KEY_DriverEmail , ""));
        user.put(KEY_DriverImage , pref.getString(KEY_DriverImage , ""));
        user.put(KEY_DriverPassword , pref.getString(KEY_DriverPassword , ""));
        user.put(KEY_DriverToken , pref.getString(KEY_DriverToken , ""));
        user.put(KEY_Driver_Device_id, pref.getString(KEY_Driver_Device_id, ""));
        user.put(KEY_Driver_flag , pref.getString(KEY_Driver_flag , ""));
        user.put(KEY_Driver_rating , pref.getString(KEY_Driver_rating , ""));
        user.put(KEY_Driver_CarTypeId , pref.getString(KEY_Driver_CarTypeId , ""));
        user.put(KEY_Driver_ModelID, pref.getString(KEY_Driver_ModelID, ""));
        user.put(KEY_Driver_CAR_Number , pref.getString(KEY_Driver_CAR_Number , ""));
        user.put(KEY_Driver_CityId , pref.getString(KEY_Driver_CityId , ""));
        user.put(KEY_Driver_registeration_date , pref.getString(KEY_Driver_registeration_date , ""));
        user.put(KEY_Driver_lisence , pref.getString(KEY_Driver_lisence , ""));
        user.put(KEY_Driver_RC , pref.getString(KEY_Driver_RC , ""));
        user.put(KEY_Driver_insurence , pref.getString(KEY_Driver_insurence , ""));
        user.put(KEY_Driver_Other_DOC , pref.getString(KEY_Driver_Other_DOC , ""));
        user.put(KEY_LastUpdate , pref.getString(KEY_LastUpdate , ""));
        user.put(KEY_LastUpdateDate , pref.getString(KEY_LastUpdateDate , ""));
        user.put(KEY_Completed_Rides , pref.getString(KEY_Completed_Rides , ""));
        user.put(KEY_Rejected_Rides  , pref.getString(KEY_Rejected_Rides  , ""));
        user.put(KEY_Cancelled_Rides , pref.getString(KEY_Cancelled_Rides , ""));
        user.put(KEY_Driver_login_logout , pref.getString(KEY_Driver_login_logout , ""));
        user.put(KEY_Driver_Busy_Status , pref.getString(KEY_Driver_Busy_Status , ""));
        user.put(KEY_Driver_Online_Offline_Status , pref.getString(KEY_Driver_Online_Offline_Status , ""));
        user.put(KEY_Detail_Status , pref.getString(KEY_Detail_Status , ""));
        user.put(KEY_Admin_Status  , pref.getString(KEY_Admin_Status  , ""));
        user.put(KEY_CarTypeName , pref.getString(KEY_CarTypeName , ""));
        user.put(KEY_CarModelName , pref.getString(KEY_CarModelName , ""));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }


    public void setonline_offline(boolean value ){
        if(value){
            editor.putString(KEY_Driver_Online_Offline_Status, "1");
            editor.commit();
        }else if (!value){
            editor.putString(KEY_Driver_Online_Offline_Status, "2");
            editor.commit();
        }
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
