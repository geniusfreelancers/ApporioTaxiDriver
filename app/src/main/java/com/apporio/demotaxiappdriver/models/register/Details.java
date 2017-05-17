
package com.apporio.demotaxiappdriver.models.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;
    @SerializedName("driver_image")
    @Expose
    private String driverImage;
    @SerializedName("driver_password")
    @Expose
    private String driverPassword;

    @SerializedName("driver_token")
    @Expose
    private String driverToken;

    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("car_type_id")
    @Expose
    private String carTypeId;
    @SerializedName("car_model_id")
    @Expose
    private String carModelId;
    @SerializedName("car_number")
    @Expose
    private String carNumber;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("register_date")
    @Expose
    private String registerDate;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("rc")
    @Expose
    private String rc;
    @SerializedName("insurance")
    @Expose
    private String insurance;
    @SerializedName("current_lat")
    @Expose
    private String currentLat;
    @SerializedName("current_long")
    @Expose
    private String currentLong;
    @SerializedName("current_location")
    @Expose
    private String currentLocation;
    @SerializedName("completed_rides")
    @Expose
    private String completedRides;
    @SerializedName("reject_rides")
    @Expose
    private String rejectRides;
    @SerializedName("cancelled_rides")
    @Expose
    private String cancelledRides;
    @SerializedName("login_logout")
    @Expose
    private String loginLogout;
    @SerializedName("busy")
    @Expose
    private String busy;
    @SerializedName("online_offline")
    @Expose
    private String onlineOffline;
    @SerializedName("driver_admin_status")
    @Expose
    private String status;

    @SerializedName("detail_status")
    @Expose
    private String detailStatus;

    @SerializedName("car_type_name")
    @Expose
    private String carTypeName;
    @SerializedName("car_model_name")
    @Expose
    private String carModelName;

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
     *     The driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * 
     * @param driverName
     *     The driver_name
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * 
     * @return
     *     The driverEmail
     */
    public String getDriverEmail() {
        return driverEmail;
    }

    /**
     * 
     * @param driverEmail
     *     The driver_email
     */
    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    /**
     * 
     * @return
     *     The driverPhone
     */
    public String getDriverPhone() {
        return driverPhone;
    }

    /**
     * 
     * @param driverPhone
     *     The driver_phone
     */
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    /**
     * 
     * @return
     *     The driverImage
     */
    public String getDriverImage() {
        return driverImage;
    }

    /**
     * 
     * @param driverImage
     *     The driver_image
     */
    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    /**
     * 
     * @return
     *     The driverPassword
     */
    public String getDriverPassword() {
        return driverPassword;
    }

    /**
     * 
     * @param driverPassword
     *     The driver_password
     */
    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    /**
     * 
     * @return
     *     The deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 
     * @param deviceId
     *     The device_id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 
     * @return
     *     The flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 
     * @param flag
     *     The flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The carTypeId
     */
    public String getCarTypeId() {
        return carTypeId;
    }

    /**
     * 
     * @param carTypeId
     *     The car_type_id
     */
    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    /**
     * 
     * @return
     *     The carModelId
     */
    public String getCarModelId() {
        return carModelId;
    }

    /**
     * 
     * @param carModelId
     *     The car_model_id
     */
    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    /**
     * 
     * @return
     *     The carNumber
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * 
     * @param carNumber
     *     The car_number
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * 
     * @return
     *     The cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 
     * @param cityId
     *     The city_id
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * @return
     *     The registerDate
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * 
     * @param registerDate
     *     The register_date
     */
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 
     * @return
     *     The license
     */
    public String getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * 
     * @return
     *     The rc
     */
    public String getRc() {
        return rc;
    }

    /**
     * 
     * @param rc
     *     The rc
     */
    public void setRc(String rc) {
        this.rc = rc;
    }

    /**
     * 
     * @return
     *     The insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * 
     * @param insurance
     *     The insurance
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     * 
     * @return
     *     The currentLat
     */
    public String getCurrentLat() {
        return currentLat;
    }

    /**
     * 
     * @param currentLat
     *     The current_lat
     */
    public void setCurrentLat(String currentLat) {
        this.currentLat = currentLat;
    }

    /**
     * 
     * @return
     *     The currentLong
     */
    public String getCurrentLong() {
        return currentLong;
    }

    /**
     * 
     * @param currentLong
     *     The current_long
     */
    public void setCurrentLong(String currentLong) {
        this.currentLong = currentLong;
    }

    /**
     * 
     * @return
     *     The currentLocation
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * 
     * @param currentLocation
     *     The current_location
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * 
     * @return
     *     The completedRides
     */
    public String getCompletedRides() {
        return completedRides;
    }

    /**
     * 
     * @param completedRides
     *     The completed_rides
     */
    public void setCompletedRides(String completedRides) {
        this.completedRides = completedRides;
    }

    /**
     * 
     * @return
     *     The rejectRides
     */
    public String getRejectRides() {
        return rejectRides;
    }

    /**
     * 
     * @param rejectRides
     *     The reject_rides
     */
    public void setRejectRides(String rejectRides) {
        this.rejectRides = rejectRides;
    }

    /**
     * 
     * @return
     *     The cancelledRides
     */
    public String getCancelledRides() {
        return cancelledRides;
    }

    /**
     * 
     * @param cancelledRides
     *     The cancelled_rides
     */
    public void setCancelledRides(String cancelledRides) {
        this.cancelledRides = cancelledRides;
    }

    /**
     * 
     * @return
     *     The loginLogout
     */
    public String getLoginLogout() {
        return loginLogout;
    }

    /**
     * 
     * @param loginLogout
     *     The login_logout
     */
    public void setLoginLogout(String loginLogout) {
        this.loginLogout = loginLogout;
    }

    /**
     * 
     * @return
     *     The busy
     */
    public String getBusy() {
        return busy;
    }

    /**
     * 
     * @param busy
     *     The busy
     */
    public void setBusy(String busy) {
        this.busy = busy;
    }

    /**
     * 
     * @return
     *     The onlineOffline
     */
    public String getOnlineOffline() {
        return onlineOffline;
    }

    /**
     * 
     * @param onlineOffline
     *     The online_offline
     */
    public void setOnlineOffline(String onlineOffline) {
        this.onlineOffline = onlineOffline;
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


    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public String getDriverToken() {
        return driverToken;
    }

    public void setDriverToken(String driverToken) {
        this.driverToken = driverToken;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }
}
