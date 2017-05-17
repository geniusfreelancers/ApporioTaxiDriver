
package com.apporio.demotaxiappdriver.models.updatelatlongdriver;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("trip_id")
    @Expose
    private String tripId;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("driver_lat")
    @Expose
    private String driverLat;
    @SerializedName("driver_long")
    @Expose
    private String driverLong;

    /**
     * 
     * @return
     *     The tripId
     */
    public String getTripId() {
        return tripId;
    }

    /**
     * 
     * @param tripId
     *     The trip_id
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    /**
     * 
     * @return
     *     The driverId
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * 
     * @param driverId
     *     The driver_id
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    /**
     * 
     * @return
     *     The customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 
     * @param customerId
     *     The customer_id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 
     * @return
     *     The driverLat
     */
    public String getDriverLat() {
        return driverLat;
    }

    /**
     * 
     * @param driverLat
     *     The driver_lat
     */
    public void setDriverLat(String driverLat) {
        this.driverLat = driverLat;
    }

    /**
     * 
     * @return
     *     The driverLong
     */
    public String getDriverLong() {
        return driverLong;
    }

    /**
     * 
     * @param driverLong
     *     The driver_long
     */
    public void setDriverLong(String driverLong) {
        this.driverLong = driverLong;
    }

}
