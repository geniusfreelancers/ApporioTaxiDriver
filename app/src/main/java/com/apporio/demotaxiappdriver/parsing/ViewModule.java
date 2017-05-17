package com.apporio.demotaxiappdriver.parsing;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.apporio.demotaxiappdriver.others.ApiFetcher;
import com.apporio.demotaxiappdriver.urls.Apis;

import com.apporio.demotaxiappdriver.logger.Logger;

public class ViewModule implements Apis {
    ApiFetcher apiFetcher;

    public ViewModule(ApiFetcher apiFetcher) {
        this.apiFetcher = apiFetcher;
    }

    public void viewUserInfoApi(String customer_id,String driver_token,String language_id) {

        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_RUNNING);
        AndroidNetworking.post(viewUserInfo)
                .addBodyParameter("user_id", customer_id)
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
                        apiFetcher.onFetchComplete("" + response, "View User Info");
                    }

                    @Override
                    public void onError(ANError anError) {
                        apiFetcher.onAPIRunningState(ApiFetcher.KEY_API_IS_STOPPED);
                        apiFetcher.onFetchFailed(anError);
                        Logger.e("Error Body        "+anError.getErrorBody());
                        Logger.e("Error Code        "+anError.getErrorCode());
                        Logger.e("Error Detail      "+anError.getErrorDetail());
                        Logger.e("Error Message     "+anError.getMessage());
                        Logger.e("Error Localized   "+anError.getLocalizedMessage());
                    }
                });
    }
}
