package com.apporio.demotaxiappdriver.samwork;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;

import static com.apporio.demotaxiappdriver.samwork.ApiManager.APIFETCHER.KEY_API_IS_STARTED;
import static com.apporio.demotaxiappdriver.samwork.ApiManager.APIFETCHER.KEY_API_IS_STOPPED;


/**
 * Created by samir on 30/01/17.
 */

public class ApiManager {

    public Context context;
    String url;
    HashMap map;
    GsonBuilder gsonBuilder;
    Gson gson;
    APIFETCHER apifetcher;

    private static final String TAG = "APIExecution";


    public ApiManager(APIFETCHER apifetcher) {
        this.context = context;
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        map = new HashMap();
        this.apifetcher = apifetcher;
    }


    @SuppressLint("LongLogTag")
    public void execution_method_post(final String tag, String url, HashMap<String, String> bodyparameter) {
        Log.d("** Executing API (POST) ", "Hashparameters => " + bodyparameter);
        Log.d("** Executing API (POST) ", "Name => " + tag + "  " + "URL => " + url);
        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.post("" + url)
                .addBodyParameter(bodyparameter)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("*** RESPONSE ", "" + jsonObject);
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        apifetcher.onFetchComplete(jsonObject, tag);
                    }

                    @Override
                    public void onError(ANError anError) {
                        apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                        Log.e("errror", "" + anError.getErrorBody());
                        Log.e("errror", "" + anError.getErrorDetail());
                        Log.e("errror", "" + anError.getMessage());
                        Log.e("error", "" + anError.getStackTrace());
                        Log.e("error", "" + anError.getCause());
                    }
                });
    }

    public void execution_method_get(final String tag, String url) {

        Log.d("** Executing API ", "Name => " + tag + "  " + "URL => " + url);


        apifetcher.onAPIRunningState(KEY_API_IS_STARTED , tag);
        AndroidNetworking.get(url)
                .setTag(this).setPriority(Priority.MEDIUM)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
                        Log.d(TAG, " timeTakenInMillis : " + timeTakenInMillis);
                        Log.d(TAG, " bytesSent : " + bytesSent);
                        Log.d(TAG, " bytesReceived : " + bytesReceived);
                        Log.d(TAG, " isFromCache : " + isFromCache);
                    }
                }).getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(final JSONObject jsonObject) {
                Log.d("*** RESPONSE ", "" + jsonObject);
                apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                apifetcher.onFetchComplete(jsonObject, tag);
            }

            @Override
            public void onError(ANError anError) {
                apifetcher.onAPIRunningState(KEY_API_IS_STOPPED , tag);
                Log.e("errror", "" + anError.getErrorBody());
                Log.e("errror", "" + anError.getErrorDetail());
                Log.e("errror", "" + anError.getMessage());
                Log.e("error", "" + anError.getStackTrace());
                Log.e("error", "" + anError.getCause());
            }
        });
    }

    public interface APIFETCHER {

        public static int KEY_API_IS_STARTED = 0;
        public static int KEY_API_IS_RUNNING = 1;
        public static int KEY_API_IS_STOPPED = 2;
        public static int KEY_API_IS_ERRORED = 3;

        void onAPIRunningState(int a, String APINAME);  // state - API Starts(0) , API Running(1) , API Stops(2)  API Error(3)


        void onFetchComplete(Object script, String APINAME); // This will give the full script

    }
}
