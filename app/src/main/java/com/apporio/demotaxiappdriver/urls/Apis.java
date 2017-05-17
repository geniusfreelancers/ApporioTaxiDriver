package com.apporio.demotaxiappdriver.urls;

import com.apporio.demotaxiappdriver.Config;

public interface Apis {


//    String baseDomain = "http://apporio.co.uk/apporiotaxi/api/";

    String baseDomain = ""+ Config.app_config.getResponse().getDriver_config().getBase_url();
//    String imageDomain = "http://apporio.co.uk/apporiotaxi/";


//    String baseDomain = "http://apporioinfolabs.com/apporiotaxi/api/";
//    String imageDomain = "http://apporioinfolabs.com/apporiotaxi/";

    String register = baseDomain + "register_driver.php";
    String registerDocs = baseDomain + "register_driver_docs.php";
    String login = baseDomain + "login_driver.php";
    String changePassword = baseDomain + "change_password_driver.php";
    String forgotPassword = baseDomain + "forgot_password_driver.php";
    String deviceId = baseDomain + "deviceid_driver.php";
    String editProfile = baseDomain + "edit_profile_driver.php";
    String logout = baseDomain + "logout_driver.php";

    String viewCities = baseDomain + "city.php";
    String viewCarByCities = baseDomain + "car_by_city.php";
    String viewCarModels = baseDomain + "car_model.php";
//    String viewDocs = baseDomain + "document_type.php";

    String viewUserInfo = baseDomain + "view_user_info.php";

    String onlineOffline = baseDomain + "online_offline.php";
    String updateLatLong = baseDomain + "driver_latlong.php";
    String updateRideLatLong = baseDomain + "ride_lat_long.php";  // api run to track driver before arrived
    String trackRideLatLong = baseDomain + "track_latlong.php";   // for calculating bill , run after after begin ride
    String ratingDriver = baseDomain + "rating_driver.php";

    String viewRideInfo = baseDomain + "view_ride_info_driver.php";
    String viewRides = baseDomain + "view_rides_driver.php";
    String viewEarnings = baseDomain + "driver_earnings.php";

    String cancelRide = baseDomain + "ride_cancel_driver.php";
    String cancelReason = baseDomain + "cancel_reason_driver.php";
    String acceptRide = baseDomain + "ride_accept.php";
    String rejectRide = baseDomain + "ride_reject.php";
    String arrivedTrip = baseDomain + "ride_arrived.php";
    String beginTrip = baseDomain + "ride_start.php";
    String endTrip = baseDomain + "ride_end.php";
    String endTripMeter = baseDomain +"ride_end_meter.php";

    String newRideSync = baseDomain + "new_ride_sync.php";

    String forceUpdate = baseDomain + "force_update.php";
    String update = baseDomain + "update.php";

    String callSupport = baseDomain + "call_support.php";
    String aboutUs = baseDomain + "about.php";
    String tC = baseDomain + "tc.php";
    String customer_support = baseDomain+"customer_support.php";   //  driver_id=4&name=1&email=12&phone=&query=hello

    String viewDoneRide = Config.app_config.getResponse().getUser_config().getBase_url() + "view_done_ride_info.php";

    String AppConfig = "http://demo9170987.mockable.io/ApporioTaxiConfig";
}
