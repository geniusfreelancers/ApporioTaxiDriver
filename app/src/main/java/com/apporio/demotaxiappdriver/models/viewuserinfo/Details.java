
package com.apporio.demotaxiappdriver.models.viewuserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("register_date")
    @Expose
    private String registerDate;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("free_rides")
    @Expose
    private String freeRides;
    @SerializedName("referral_code_send")
    @Expose
    private String referralCodeSend;
    @SerializedName("phone_verified")
    @Expose
    private String phoneVerified;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("password_created")
    @Expose
    private String passwordCreated;
    @SerializedName("social_token")
    @Expose
    private String socialToken;
    @SerializedName("token_created")
    @Expose
    private String tokenCreated;
    @SerializedName("login_logout")
    @Expose
    private String loginLogout;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("status")
    @Expose
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getFreeRides() {
        return freeRides;
    }

    public void setFreeRides(String freeRides) {
        this.freeRides = freeRides;
    }

    public String getReferralCodeSend() {
        return referralCodeSend;
    }

    public void setReferralCodeSend(String referralCodeSend) {
        this.referralCodeSend = referralCodeSend;
    }

    public String getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(String phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPasswordCreated() {
        return passwordCreated;
    }

    public void setPasswordCreated(String passwordCreated) {
        this.passwordCreated = passwordCreated;
    }

    public String getSocialToken() {
        return socialToken;
    }

    public void setSocialToken(String socialToken) {
        this.socialToken = socialToken;
    }

    public String getTokenCreated() {
        return tokenCreated;
    }

    public void setTokenCreated(String tokenCreated) {
        this.tokenCreated = tokenCreated;
    }

    public String getLoginLogout() {
        return loginLogout;
    }

    public void setLoginLogout(String loginLogout) {
        this.loginLogout = loginLogout;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
