package com.apporio.demotaxiappdriver.models;

/**
 * Created by bhuvneshapporio on 3/10/2017.
 */

public class DriverOnlineOffline {

    public String online_offline_status;

    public DriverOnlineOffline() {
    }

    public DriverOnlineOffline(String online_offline_status) {
        this.online_offline_status = online_offline_status;
    }

    public String getOnline_offline_status() {
        return online_offline_status;
    }

    public void setOnline_offline_status(String online_offline_status) {
        this.online_offline_status = online_offline_status;
    }
}
