package com.apporio.demotaxiappdriver.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by bhuvneshapporio on 3/10/2017.
 */


@IgnoreExtraProperties
public class DriverLocation {


    public String driver_id;
    public String driver_name;
    public String driver_phone;
    public String driver_email;
    public String driver_image;
    public String driver_password;
    public String driver_token;
    public String driver_device_id;
    public String driver_flag;
    public String driver_rating;
    public String driver_car_type_id;
    public String driver_model_id;
    public String driver_number;
    public String driver_city_id;
    public String driver_registration_date;
    public String driver_lisence;
    public String driver_rc;
    public String driver_insurence;
    public String driver_other_doc;
    public String driver_last_update;
    public String driver_last_update_date;
    public String driver_completed_rides;
    public String driver_rejected_rides;
    public String driver_cancelled_rides;
    public String driver_login_logout;
    public String driver_busy_status;
    public String driver_online_offline_status;
    public String driver_detail_status;
    public String driver_admin_status;
    public String driver_car_type_name;
    public String driver_car_model_name;
    public String driver_current_latitude;
    public String driver_current_longitude;
    public String driver_location_text;
    public String timestamp;
    public String bearingfactor;









    public DriverLocation() {
    }

    public DriverLocation(String DriverId ,
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
                          String CarModelName,
                          String driver_current_latitude,
                          String driver_current_longitude,
                          String driver_location_text,
                          String timestamp,
                          String bearingfactor) {



        this.driver_id = DriverId ;
        this.driver_name = DriverName ;
        this.driver_phone = DriverPhoneNumber ;
        this.driver_email = DriverEmail ;
        this.driver_image = DriverImage ;
        this.driver_password = DriverPassword;
        this.driver_token = DriverToken;
        this.driver_device_id = Driver_Device_id;
        this.driver_flag = Driver_flag;
        this.driver_rating = Driver_rating;
        this.driver_car_type_id = Driver_CarTypeId;
        this.driver_model_id =Driver_ModelID;
        this.driver_number =Driver_CAR_Number;
        this.driver_city_id = Driver_CityId;
        this.driver_registration_date = Driver_registeration_date;
        this.driver_lisence = Driver_lisence;
        this.driver_rc = Driver_RC;
        this.driver_insurence = Driver_insurence;
        this.driver_other_doc = Driver_Other_DOC;
        this.driver_last_update = LastUpdate;
        this.driver_last_update_date = LastUpdateDate;
        this.driver_completed_rides = Completed_Rides;
        this.driver_rejected_rides = Rejected_Rides;
        this.driver_cancelled_rides = Cancelled_Rides;
        this.driver_login_logout = Driver_login_logout;
        this.driver_busy_status = Driver_Busy_Status;
        this.driver_online_offline_status = Driver_Online_Offline_Status;
        this.driver_detail_status = Detail_Status;
        this.driver_admin_status = Admin_Status;
        this.driver_car_type_name = CarTypeName;
        this.driver_car_model_name = CarModelName;
        this.driver_current_latitude = driver_current_latitude;
        this.driver_current_longitude = driver_current_longitude;
        this.driver_location_text = driver_location_text;
        this.timestamp = timestamp;
        this.bearingfactor = bearingfactor ;

    }






}
