
package com.apporio.demotaxiappdriver.models.earnings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("ride_date")
    @Expose
    private String rideDate;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("rides")
    @Expose
    private Integer rides;

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public Integer getRides() {
        return rides;
    }

    public void setRides(Integer rides) {
        this.rides = rides;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
