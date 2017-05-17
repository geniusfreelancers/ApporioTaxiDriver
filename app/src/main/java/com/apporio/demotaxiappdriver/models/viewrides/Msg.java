
package com.apporio.demotaxiappdriver.models.viewrides;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("ride_id")
    @Expose
    private String rideId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pickup_lat")
    @Expose
    private String pickupLat;
    @SerializedName("pickup_long")
    @Expose
    private String pickupLong;
    @SerializedName("drop_lat")
    @Expose
    private String dropLat;
    @SerializedName("drop_long")
    @Expose
    private String dropLong;
    @SerializedName("pickup_location")
    @Expose
    private String pickupLocation;
    @SerializedName("drop_location")
    @Expose
    private String dropLocation;
    @SerializedName("ride_date")
    @Expose
    private String rideDate;
    @SerializedName("ride_time")
    @Expose
    private String rideTime;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("ride_status")
    @Expose
    private String rideStatus;
    @SerializedName("ride_type")
    @Expose
    private String rideType;
    @SerializedName("begin_lat")
    @Expose
    private String beginLat;
    @SerializedName("begin_long")
    @Expose
    private String beginLong;
    @SerializedName("arrived_time")
    @Expose
    private String arrivedTime;
    @SerializedName("begin_time")
    @Expose
    private String beginTime;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("begin_location")
    @Expose
    private String beginLocation;
    @SerializedName("end_location")
    @Expose
    private String endLocation;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("time")
    @Expose
    private String time;


    @SerializedName("user_name")
    @Expose
    private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
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
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The pickupLat
     */
    public String getPickupLat() {
        return pickupLat;
    }

    /**
     * 
     * @param pickupLat
     *     The pickup_lat
     */
    public void setPickupLat(String pickupLat) {
        this.pickupLat = pickupLat;
    }

    /**
     * 
     * @return
     *     The pickupLong
     */
    public String getPickupLong() {
        return pickupLong;
    }

    /**
     * 
     * @param pickupLong
     *     The pickup_long
     */
    public void setPickupLong(String pickupLong) {
        this.pickupLong = pickupLong;
    }

    /**
     * 
     * @return
     *     The dropLat
     */
    public String getDropLat() {
        return dropLat;
    }

    /**
     * 
     * @param dropLat
     *     The drop_lat
     */
    public void setDropLat(String dropLat) {
        this.dropLat = dropLat;
    }

    /**
     * 
     * @return
     *     The dropLong
     */
    public String getDropLong() {
        return dropLong;
    }

    /**
     * 
     * @param dropLong
     *     The drop_long
     */
    public void setDropLong(String dropLong) {
        this.dropLong = dropLong;
    }

    /**
     * 
     * @return
     *     The pickupLocation
     */
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * 
     * @param pickupLocation
     *     The pickup_location
     */
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * 
     * @return
     *     The dropLocation
     */
    public String getDropLocation() {
        return dropLocation;
    }

    /**
     * 
     * @param dropLocation
     *     The drop_location
     */
    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    /**
     * 
     * @return
     *     The rideDate
     */
    public String getRideDate() {
        return rideDate;
    }

    /**
     * 
     * @param rideDate
     *     The ride_date
     */
    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
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
     *     The rideStatus
     */
    public String getRideStatus() {
        return rideStatus;
    }

    /**
     * 
     * @param rideStatus
     *     The ride_status
     */
    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    /**
     * 
     * @return
     *     The rideType
     */
    public String getRideType() {
        return rideType;
    }

    /**
     * 
     * @param rideType
     *     The ride_type
     */
    public void setRideType(String rideType) {
        this.rideType = rideType;
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
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(String time) {
        this.time = time;
    }

}
