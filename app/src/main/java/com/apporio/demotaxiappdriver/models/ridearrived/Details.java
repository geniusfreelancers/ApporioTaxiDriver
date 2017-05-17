
package com.apporio.demotaxiappdriver.models.ridearrived;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("done_ride_id")
    @Expose
    private String doneRideId;
    @SerializedName("ride_id")
    @Expose
    private String rideId;
    @SerializedName("begin_lat")
    @Expose
    private String beginLat;
    @SerializedName("begin_long")
    @Expose
    private String beginLong;
    @SerializedName("end_lat")
    @Expose
    private String endLat;
    @SerializedName("end_long")
    @Expose
    private String endLong;
    @SerializedName("begin_location")
    @Expose
    private String beginLocation;
    @SerializedName("end_location")
    @Expose
    private String endLocation;
    @SerializedName("arrived_time")
    @Expose
    private String arrivedTime;
    @SerializedName("begin_time")
    @Expose
    private String beginTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("waiting_time")
    @Expose
    private String waitingTime;
    @SerializedName("ride_time")
    @Expose
    private String rideTime;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("tot_time")
    @Expose
    private String totTime;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;

    /**
     * 
     * @return
     *     The doneRideId
     */
    public String getDoneRideId() {
        return doneRideId;
    }

    /**
     * 
     * @param doneRideId
     *     The done_ride_id
     */
    public void setDoneRideId(String doneRideId) {
        this.doneRideId = doneRideId;
    }

    /**
     * 
     * @return
     *     The rideId
     */
    public String getRideId() {
        return rideId;
    }

    /**
     * 
     * @param rideId
     *     The ride_id
     */
    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    /**
     * 
     * @return
     *     The beginLat
     */
    public String getBeginLat() {
        return beginLat;
    }

    /**
     * 
     * @param beginLat
     *     The begin_lat
     */
    public void setBeginLat(String beginLat) {
        this.beginLat = beginLat;
    }

    /**
     * 
     * @return
     *     The beginLong
     */
    public String getBeginLong() {
        return beginLong;
    }

    /**
     * 
     * @param beginLong
     *     The begin_long
     */
    public void setBeginLong(String beginLong) {
        this.beginLong = beginLong;
    }

    /**
     * 
     * @return
     *     The endLat
     */
    public String getEndLat() {
        return endLat;
    }

    /**
     * 
     * @param endLat
     *     The end_lat
     */
    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    /**
     * 
     * @return
     *     The endLong
     */
    public String getEndLong() {
        return endLong;
    }

    /**
     * 
     * @param endLong
     *     The end_long
     */
    public void setEndLong(String endLong) {
        this.endLong = endLong;
    }

    /**
     * 
     * @return
     *     The beginLocation
     */
    public String getBeginLocation() {
        return beginLocation;
    }

    /**
     * 
     * @param beginLocation
     *     The begin_location
     */
    public void setBeginLocation(String beginLocation) {
        this.beginLocation = beginLocation;
    }

    /**
     * 
     * @return
     *     The endLocation
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     * 
     * @param endLocation
     *     The end_location
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * 
     * @return
     *     The arrivedTime
     */
    public String getArrivedTime() {
        return arrivedTime;
    }

    /**
     * 
     * @param arrivedTime
     *     The arrived_time
     */
    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    /**
     * 
     * @return
     *     The beginTime
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * 
     * @param beginTime
     *     The begin_time
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The end_time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The waitingTime
     */
    public String getWaitingTime() {
        return waitingTime;
    }

    /**
     * 
     * @param waitingTime
     *     The waiting_time
     */
    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * 
     * @return
     *     The rideTime
     */
    public String getRideTime() {
        return rideTime;
    }

    /**
     * 
     * @param rideTime
     *     The ride_time
     */
    public void setRideTime(String rideTime) {
        this.rideTime = rideTime;
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
     *     The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The totTime
     */
    public String getTotTime() {
        return totTime;
    }

    /**
     * 
     * @param totTime
     *     The tot_time
     */
    public void setTotTime(String totTime) {
        this.totTime = totTime;
    }

    /**
     * 
     * @return
     *     The paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 
     * @param paymentStatus
     *     The payment_status
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
