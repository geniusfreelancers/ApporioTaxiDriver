package com.apporio.demotaxiappdriver.parsing;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.logger.Logger;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;


public class SyncModule implements Apis {
    ApiFetcher apiFetcher;

    public SyncModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void newRideSyncApi(String ride_id, String driver_id, String language_id) {

        String url = newRideSync + "?ride_id=" + ride_id + "&driver_id=" + driver_id + "&language_id=" + language_id;
        Logger.e("newRideSync url       " + url);

        AndroidNetworking.post(newRideSync)
                .addBodyParameter("ride_id", ride_id)
                .addBodyParameter("driver_id", driver_id)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("*****new_ride_sync  response" + response);
                        apiFetcher.onFetchComplete("" + response, "New Ride Sync");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }
}
