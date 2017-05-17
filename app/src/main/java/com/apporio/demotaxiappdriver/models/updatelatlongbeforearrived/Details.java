
package com.apporio.demotaxiappdriver.models.updatelatlongbeforearrived;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("ride_track_id")
    @Expose
    private String rideTrackId;
    @SerializedName("ride_id")
    @Expose
    private String rideId;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("driver_lat")
    @Expose
    private String driverLat;
    @SerializedName("driver_long")
    @Expose
    private String driverLong;

    public String getRideTrackId() {
        return rideTrackId;
    }

    public void setRideTrackId(String rideTrackId) {
        this.rideTrackId = rideTrackId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverLat() {
        return driverLat;
    }

    public void setDriverLat(String driverLat) {
        this.driverLat = driverLat;
    }

    public String getDriverLong() {
        return driverLong;
    }

    public void setDriverLong(String driverLong) {
        this.driverLong = driverLong;
    }

}
