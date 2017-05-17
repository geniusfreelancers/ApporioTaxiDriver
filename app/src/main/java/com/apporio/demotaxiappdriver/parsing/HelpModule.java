package com.apporio.demotaxiappdriver.parsing;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;

import com.apporio.demotaxiappdriver.logger.Logger;


public class HelpModule implements Apis
{
    ApiFetcher apiFetcher;

    public HelpModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void callSupportApi(String language_id) {

        AndroidNetworking.post(callSupport)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "Call Support");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void aboutUsApi(String language_id) {

        AndroidNetworking.post(aboutUs)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "About Us");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

    public void tcApi(String language_id) {

        AndroidNetworking.post(tC)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onFetchComplete("" + response, "T and C");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }





    public void customerSupportAPI (String usr_id , String user_name  , String usermail  , String userphone ,String query ,  String language_id) {
        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STARTED);
        AndroidNetworking.post(customer_support)
                .addBodyParameter("driver_id", usr_id)
                .addBodyParameter("name", user_name)
                .addBodyParameter("email", usermail)
                .addBodyParameter("phone", userphone)
                .addBodyParameter("query", query)
                .addBodyParameter("language_id", language_id)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Logger.e("response" + response);
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);

                        apiFetcher.onFetchComplete("" + response, "customer_support");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onFetchFailed(anError);
                    }
                });
    }

}
