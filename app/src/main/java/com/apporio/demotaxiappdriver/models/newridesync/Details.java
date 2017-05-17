
package com.apporio.demotaxiappdriver.models.newridesync;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("ride_id")
    @Expose
    private String rideId;
    @SerializedName("ride_status")
    @Expose
    private String rideStatus;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

}
