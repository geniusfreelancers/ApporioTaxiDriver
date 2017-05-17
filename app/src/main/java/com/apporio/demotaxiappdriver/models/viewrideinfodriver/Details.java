
package com.apporio.demotaxiappdriver.models.viewrideinfodriver;

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
    @SerializedName("ride_image")
    @Expose
    private String rideImage;
    @SerializedName("later_date")
    @Expose
    private String laterDate;
    @SerializedName("later_time")
    @Expose
    private String laterTime;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("car_type_id")
    @Expose
    private String carTypeId;
    @SerializedName("ride_type")
    @Expose
    private String rideType;
    @SerializedName("ride_status")
    @Expose
    private String rideStatus;
    @SerializedName("reason_id")
    @Expose
    private String reasonId;
    @SerializedName("payment_option_id")
    @Expose
    private String paymentOptionId;
    @SerializedName("card_id")
    @Expose
    private String cardId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("arrived_time")
    @Expose
    private String arrivedTime;
    @SerializedName("begin_lat")
    @Expose
    private String beginLat;
    @SerializedName("begin_long")
    @Expose
    private String beginLong;
    @SerializedName("begin_location")
    @Expose
    private String beginLocation;
    @SerializedName("begin_time")
    @Expose
    private String beginTime;
    @SerializedName("end_lat")
    @Expose
    private String endLat;
    @SerializedName("end_long")
    @Expose
    private String endLong;
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
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(String pickupLat) {
        this.pickupLat = pickupLat;
    }

    public String getPickupLong() {
        return pickupLong;
    }

    public void setPickupLong(String pickupLong) {
        this.pickupLong = pickupLong;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLat() {
        return dropLat;
    }

    public void setDropLat(String dropLat) {
        this.dropLat = dropLat;
    }

    public String getDropLong() {
        return dropLong;
    }

    public void setDropLong(String dropLong) {
        this.dropLong = dropLong;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public String getRideTime() {
        return rideTime;
    }

    public void setRideTime(String rideTime) {
        this.rideTime = rideTime;
    }

    public String getRideImage() {
        return rideImage;
    }

    public void setRideImage(String rideImage) {
        this.rideImage = rideImage;
    }

    public String getLaterDate() {
        return laterDate;
    }

    public void setLaterDate(String laterDate) {
        this.laterDate = laterDate;
    }

    public String getLaterTime() {
        return laterTime;
    }

    public void setLaterTime(String laterTime) {
        this.laterTime = laterTime;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getPaymentOptionId() {
        return paymentOptionId;
    }

    public void setPaymentOptionId(String paymentOptionId) {
        this.paymentOptionId = paymentOptionId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public String getBeginLat() {
        return beginLat;
    }

    public void setBeginLat(String beginLat) {
        this.beginLat = beginLat;
    }

    public String getBeginLong() {
        return beginLong;
    }

    public void setBeginLong(String beginLong) {
        this.beginLong = beginLong;
    }

    public String getBeginLocation() {
        return beginLocation;
    }

    public void setBeginLocation(String beginLocation) {
        this.beginLocation = beginLocation;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLong() {
        return endLong;
    }

    public void setEndLong(String endLong) {
        this.endLong = endLong;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
