package com.apporio.demotaxiappdriver.parsing;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;

import com.apporio.demotaxiappdriver.logger.Logger;

public class RideModule implements Apis {

    ApiFetcher apiFetcher;

    public RideModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void myRidesApi(String driver_id, String driver_token, String language_id) {

        String url = viewRides + "?driver_id=" + driver_id+"&driver_token="+driver_token+"&language_id="+language_id;
        Log.d("***RideHistoryAPI=>"  , ""+ url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(viewRides)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("***RideHistoryResponse"  , ""+ response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "My Rides");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void earningsApi(String driver_id, String driver_token, String language_id) {
        String url = viewEarnings + "?driver_id" + driver_id;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(viewEarnings)
                .addBodyParameter("driver_id", driver_id)
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
                        apiFetcher.onFetchComplete("" + response, "My Earnings");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void acceptRideApi(String ride_id, String driver_id, String driver_token, String language_id) {
        String url = acceptRide + "?ride_id=" + ride_id + "&driver_id=" + driver_token+"&language_id=" + language_id;
        Log.d("****Receive_passenger=> " , ""+url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(acceptRide)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("****Receive_passenger_response => " , ""+response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Accept Ride");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void rejectRideApi(String ride_id, String driver_id, String driver_token, String language_id) {

        String url = rejectRide + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&ride_status=4&driver_token=" + driver_token + "&language_id=" + language_id;
        Logger.e("rejectRide url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(rejectRide)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("ride_status", "4")
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
                        apiFetcher.onFetchComplete("" + response, "Reject Ride");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void viewRideInfoApi(String ride_id, String driver_token, String language_id) {
        String url = viewRideInfo + "?ride_id=" + ride_id;
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(viewRideInfo)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_token", driver_token)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("****view_ride_info_driver"  , ""+response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "View Ride Info");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void arrivedTripApi(String ride_id, String driver_id, String arrived_time, String driver_token, String language_id) {

        String url = arrivedTrip + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&arrived_time=" + arrived_time + "&ride_status=5";
        Logger.e("url       " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(arrivedTrip)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("arrived_time", arrived_time)
                .addBodyParameter("ride_status", "5")
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
                        apiFetcher.onFetchComplete("" + response, "Arrived Trip");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void beginTripApi(String ride_id, String driver_id, String begin_lat, String begin_long, String begin_location, String begin_time, String driver_token, String language_id) {

        String url = beginTrip + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&begin_lat=" + begin_lat + "&begin_long=" + begin_long + "&begin_location=" + begin_location + "&begin_time=" + begin_time + "&ride_status=6";
        url = url.replace(" ", "%20");
        Logger.e("url           " + url);

//        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(beginTrip)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("begin_lat", begin_lat)
                .addBodyParameter("begin_long", begin_long)
                .addBodyParameter("begin_location", begin_location)
                .addBodyParameter("begin_time", begin_time)
                .addBodyParameter("ride_status", "6")
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
                        apiFetcher.onFetchComplete("" + response, "Begin Trip");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void endTripApi(String ride_id, String driver_id, String begin_lat, String begin_long, String begin_location, String end_lat, String end_long, String end_location, String end_time, String waiting_time, String ride_time, String driver_token, String language_id) {

        String url = endTrip + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&begin_lat=" + begin_lat + "&begin_long=" + begin_long + "&begin_location=" + begin_location + "&end_lat=" + end_lat + "&end_long=" + end_long + "&end_location=" + end_location + "&end_time=" + end_time + "&waiting_time=" + waiting_time + "&ride_time=" + ride_time + "&ride_status=7&distance=&driver_token=" + driver_token + "&language_id=1";
        Logger.e("end trip url      " + url);

        AndroidNetworking.post(endTrip)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("begin_lat", begin_lat)
                .addBodyParameter("begin_long", begin_long)
                .addBodyParameter("begin_location", begin_location)
                .addBodyParameter("end_lat", end_lat)
                .addBodyParameter("end_long", end_long)
                .addBodyParameter("end_location", end_location)
                .addBodyParameter("end_time", end_time)
                .addBodyParameter("waiting_time", waiting_time)
                .addBodyParameter("ride_time", ride_time)
                .addBodyParameter("ride_status", "7")
                .addBodyParameter("distance", "")
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
                        apiFetcher.onFetchComplete("" + response, "End Trip");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void cancelRideApi(String ride_id, String reason_id, String language_id) {
        String url = cancelRide + "?ride_id=" + ride_id + "&ride_status=9&reason_id=" + reason_id;
        Logger.e("url " + url);

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(cancelRide)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("ride_status", "9")
                .addBodyParameter("reason_id", reason_id)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchComplete("" + response, "Cancel Ride");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                    }
                });
    }

    public void cancelReasonApi(String language_id) {

        String url = cancelReason;
        Logger.e("url       " + url);

        AndroidNetworking.post(cancelReason)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "View Reasons");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }
}
