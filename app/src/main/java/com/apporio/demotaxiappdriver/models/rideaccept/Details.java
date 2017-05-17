
package com.apporio.demotaxiappdriver.models.rideaccept;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("ride_id")
    @Expose
    private String rideId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("pickup_lat")
    @Expose
    private String pickupLat;
    @SerializedName("pickup_long")
    @Expose
    private String pickupLong;
    @SerializedName("pickup_location")
    @Expose
    private String pickupLocation;
    @SerializedName("drop_lat")
    @Expose
    private String dropLat;
    @SerializedName("drop_long")
    @Expose
    private String dropLong;
    @SerializedName("drop_location")
    @Expose
    private String dropLocation;
    @SerializedName("ride_date")
    @Expose
    private String rideDate;
    @SerializedName("ride_time")
    @Expose
    private String rideTime;
    @SerializedName("later_date")
    @Expose
    private String laterDate;
    @SerializedName("later_time")
    @Expose
    private String laterTime;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("ride_type")
    @Expose
    private String rideType;
    @SerializedName("ride_status")
    @Expose
    private String rideStatus;
    @SerializedName("status")
    @Expose
    private String status;

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
     *     The couponCode
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * 
     * @param couponCode
     *     The coupon_code
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
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
     *     The laterDate
     */
    public String getLaterDate() {
        return laterDate;
    }

    /**
     * 
     * @param laterDate
     *     The later_date
     */
    public void setLaterDate(String laterDate) {
        this.laterDate = laterDate;
    }

    /**
     * 
     * @return
     *     The laterTime
     */
    public String getLaterTime() {
        return laterTime;
    }

    /**
     * 
     * @param laterTime
     *     The later_time
     */
    public void setLaterTime(String laterTime) {
        this.laterTime = laterTime;
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
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
