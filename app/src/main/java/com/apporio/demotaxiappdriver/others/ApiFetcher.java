package com.apporio.demotaxiappdriver.others;

import com.androidnetworking.error.ANError;

public interface ApiFetcher {

    public static int KEY_API_IS_STARTED = 0;
    public static int KEY_API_IS_RUNNING = 1;
    public static int KEY_API_IS_STOPPED = 2;
    public static int KEY_API_IS_ERRORED = 3;

    void onAPIRunningState(int a);  // state - API Starts(0) , API Running(1) , API Stops(2)  API Error(3)

    void onFetchProgress(int progress);  // This Will useful when downloading file

    void onFetchComplete(String response, String apiName); // This will give the full script

    void onFetchFailed(ANError error);  // This will give the cause of error if occurred

    void WhichApi(String apiName);

}
