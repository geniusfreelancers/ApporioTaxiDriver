package com.apporio.demotaxiappdriver.parsing;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;

import com.apporio.demotaxiappdriver.logger.Logger;

/**
 * Created by Bhuvneshwar on 11/2/2016.
 */
public class DriverModule implements Apis {
    ApiFetcher apiFetcher;

    public DriverModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void onlineOfflineApi(String driver_id, String status, String language_id) {

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", status)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Online Offline");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }



    public void onlineApi(String driver_id, String on_off_status, String driver_token, String language_id) {

        String url = onlineOffline + "?driver_id=" + driver_id + "&online_offline=" + on_off_status;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", on_off_status)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Online");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void onlineApiStart(String driver_id, String on_off_status, String driver_token, String language_id) {

        String url = onlineOffline + "?driver_id=" + driver_id + "&online_offline=" + on_off_status;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", on_off_status)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Online Start");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void offlineApi(String driver_id, String on_off_status, String driver_token, String language_id) {

        String url = onlineOffline + "?driver_id=" + driver_id + "&online_offline=" + on_off_status;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", on_off_status)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Offline");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void offlineApiFinish(String driver_id, String on_off_status, String driver_token, String language_id) {

        String url = onlineOffline + "?driver_id=" + driver_id + "&online_offline=" + on_off_status;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", on_off_status)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Offline Finish");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void offlineApiFinishDestroy(String driver_id, String on_off_status, String driver_token, String language_id) {

        String url = onlineOffline + "?driver_id=" + driver_id + "&online_offline=" + on_off_status;
        Logger.e("url       " + url);

//        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(onlineOffline)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("online_offline", on_off_status)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
//                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Offline Finish");
                    }

                    @Override
                    public void onError(ANError anError) {
//                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void updateLatLongApi(String driver_id, String latitude, String longitude, String location, String driver_token, String language_id) {

        String url = updateLatLong + "?driver_id=" + driver_id + "&current_lat=" + latitude + "&current_long=" + longitude + "&current_location=" + location + "&driver_token=" + driver_token;
        Logger.e("url lat long      " + url);

        AndroidNetworking.post(updateLatLong)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("current_lat", latitude)
                .addBodyParameter("current_long", longitude)
                .addBodyParameter("current_location", location)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        apiFetcher.onFetchComplete("" + response, "Update Lat Long");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void updateRideDriverLocationApi(String ride_id, String driver_id, String customer_id, String driver_lat, String driver_long, String driver_token, String language_id) {

        Logger.e("url before        " + updateRideLatLong + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&user_id=" + customer_id + "&driver_lat=" + driver_lat + "&driver_long=" + driver_long + "&driver_token=" + driver_token);

        AndroidNetworking.post(updateRideLatLong)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("user_id", customer_id)
                .addBodyParameter("driver_lat", driver_lat)
                .addBodyParameter("driver_long", driver_long)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
//                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Update Ride Driver Location");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void rideTrackApi(String ride_id, String driver_id, String driver_lat, String driver_long, String driver_token, String language_id) {

        String url = trackRideLatLong + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&driver_lat=" + driver_lat + "&driver_long=" + driver_long;
//        Logger.e("url       " + url);

        AndroidNetworking.post(trackRideLatLong)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("driver_lat", driver_lat)
                .addBodyParameter("driver_long", driver_long)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
//                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Ride Track");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void ratingApi(String ride_id, String driver_id, String user_id, String rating, String driver_token, String language_id) {

        String url = ratingDriver + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&user_id=" + user_id + "&rating_star=" + rating + "&comment=1&driver_token=" + driver_token + "&language_id=" + language_id;
        Logger.e("ratingDriver url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(ratingDriver)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("user_id", user_id)
                .addBodyParameter("rating_star", rating)
                .addBodyParameter("comment", "1")
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Rating");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }

    public void forceUpdateApi(String version_code, String language_id) {

        String url = forceUpdate + "?version_id=2&version_code=" + version_code;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(forceUpdate)
                .addBodyParameter("version_id", "2")
                .addBodyParameter("version_code", version_code)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Force Update");
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void updateApi(String version_code, String language_id) {

        String url = update + "?version_id=2&version_code=" + version_code;
        Logger.e("url       " + url);

        AndroidNetworking.post(update)
                .addBodyParameter("version_id", "2")
                .addBodyParameter("version_code", version_code)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Update");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }




    public void setAppConfig() {
        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.get(AppConfig)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "api_config");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        " + anError.getErrorBody());
                        Logger.e("Error Code        " + anError.getErrorCode());
                        Logger.e("Error Detail      " + anError.getErrorDetail());
                        Logger.e("Error Message     " + anError.getMessage());
                        Logger.e("Error Localized   " + anError.getLocalizedMessage());
                    }
                });
    }





}
