
package com.apporio.demotaxiappdriver.models.rideaccept;

public class RideAccept {


    /**
     * result : 1
     * msg : Booking Accepted!!
     * details : {"ride_id":"687","user_id":"63","coupon_code":"","pickup_lat":"28.412121178592297","pickup_long":"77.043268494308","pickup_location":"68, Plaza Street,Block S, Uppal Southend, Sector 49,Gurugram, Haryana 122018,","drop_lat":"28.35647235681196","drop_long":"77.03043412417173","drop_location":"Unnamed Road,Teekli,Tikli, Haryana 122103,","ride_date":"Wednesday, May 17","ride_time":"13:45:54","last_time_stamp":"01:45:59 PM","ride_image":"https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=600x300&maptype=roadmap&markers=color:green|label:S|28.412121178592297,77.043268494308&markers=color:red|label:D|28.35647235681196,77.03043412417173&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4","later_date":"","later_time":"","driver_id":"98","car_type_id":"5","ride_type":"1","ride_status":"3","reason_id":"0","payment_option_id":"1","card_id":"0","ride_admin_status":"1","user_name":"samir goel .","user_email":"samirgoel3normal@gmail.com","user_phone":"+919650923089","user_password":"123456","user_image":"","register_date":"Monday, May 15","device_id":"cpGAFZydM-Y:APA91bEHmP87nZA2YeC9gYTq4YUrTrYxDyEAJ-7yg9L3b8vLQOH7vM63eceHrvAE8mQD6y3YNa8B4CrAEu2FWyzRd3azaUcmSdgdEQYTIxAjMrQjVT2sSfCMNVBFamsNilAfNOZvqeDW","flag":"2","referral_code":"","free_rides":"0","referral_code_send":"0","phone_verified":"0","email_verified":"0","password_created":"0","facebook_id":"","facebook_mail":"","facebook_image":"","facebook_firstname":"","facebook_lastname":"","google_id":"112279197400670101492","google_name":"samir goel","google_mail":"samirgoel3@gmail.com","google_image":"google user image","google_token":"","facebook_token":"","token_created":"0","login_logout":"1","rating":"3.625","status":"1"}
     */

    private int result;
    private String msg;
    private DetailsBean details;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * ride_id : 687
         * user_id : 63
         * coupon_code :
         * pickup_lat : 28.412121178592297
         * pickup_long : 77.043268494308
         * pickup_location : 68, Plaza Street,Block S, Uppal Southend, Sector 49,Gurugram, Haryana 122018,
         * drop_lat : 28.35647235681196
         * drop_long : 77.03043412417173
         * drop_location : Unnamed Road,Teekli,Tikli, Haryana 122103,
         * ride_date : Wednesday, May 17
         * ride_time : 13:45:54
         * last_time_stamp : 01:45:59 PM
         * ride_image : https:maps.googleapis.com/maps/api/staticmap?center=&zoom=12&size=600x300&maptype=roadmap&markers=color:green|label:S|28.412121178592297,77.043268494308&markers=color:red|label:D|28.35647235681196,77.03043412417173&key=AIzaSyAIFe17P91Mfez3T6cqk7hfDSyvMO812Z4
         * later_date :
         * later_time :
         * driver_id : 98
         * car_type_id : 5
         * ride_type : 1
         * ride_status : 3
         * reason_id : 0
         * payment_option_id : 1
         * card_id : 0
         * ride_admin_status : 1
         * user_name : samir goel .
         * user_email : samirgoel3normal@gmail.com
         * user_phone : +919650923089
         * user_password : 123456
         * user_image :
         * register_date : Monday, May 15
         * device_id : cpGAFZydM-Y:APA91bEHmP87nZA2YeC9gYTq4YUrTrYxDyEAJ-7yg9L3b8vLQOH7vM63eceHrvAE8mQD6y3YNa8B4CrAEu2FWyzRd3azaUcmSdgdEQYTIxAjMrQjVT2sSfCMNVBFamsNilAfNOZvqeDW
         * flag : 2
         * referral_code :
         * free_rides : 0
         * referral_code_send : 0
         * phone_verified : 0
         * email_verified : 0
         * password_created : 0
         * facebook_id :
         * facebook_mail :
         * facebook_image :
         * facebook_firstname :
         * facebook_lastname :
         * google_id : 112279197400670101492
         * google_name : samir goel
         * google_mail : samirgoel3@gmail.com
         * google_image : google user image
         * google_token :
         * facebook_token :
         * token_created : 0
         * login_logout : 1
         * rating : 3.625
         * status : 1
         */

        private String ride_id;
        private String user_id;
        private String coupon_code;
        private String pickup_lat;
        private String pickup_long;
        private String pickup_location;
        private String drop_lat;
        private String drop_long;
        private String drop_location;
        private String ride_date;
        private String ride_time;
        private String last_time_stamp;
        private String ride_image;
        private String later_date;
        private String later_time;
        private String driver_id;
        private String car_type_id;
        private String ride_type;
        private String ride_status;
        private String reason_id;
        private String payment_option_id;
        private String card_id;
        private String ride_admin_status;
        private String user_name;
        private String user_email;
        private String user_phone;
        private String user_password;
        private String user_image;
        private String register_date;
        private String device_id;
        private String flag;
        private String referral_code;
        private String free_rides;
        private String referral_code_send;
        private String phone_verified;
        private String email_verified;
        private String password_created;
        private String facebook_id;
        private String facebook_mail;
        private String facebook_image;
        private String facebook_firstname;
        private String facebook_lastname;
        private String google_id;
        private String google_name;
        private String google_mail;
        private String google_image;
        private String google_token;
        private String facebook_token;
        private String token_created;
        private String login_logout;
        private String rating;
        private String status;

        public String getRide_id() {
            return ride_id;
        }

        public void setRide_id(String ride_id) {
            this.ride_id = ride_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getPickup_lat() {
            return pickup_lat;
        }

        public void setPickup_lat(String pickup_lat) {
            this.pickup_lat = pickup_lat;
        }

        public String getPickup_long() {
            return pickup_long;
        }

        public void setPickup_long(String pickup_long) {
            this.pickup_long = pickup_long;
        }

        public String getPickup_location() {
            return pickup_location;
        }

        public void setPickup_location(String pickup_location) {
            this.pickup_location = pickup_location;
        }

        public String getDrop_lat() {
            return drop_lat;
        }

        public void setDrop_lat(String drop_lat) {
            this.drop_lat = drop_lat;
        }

        public String getDrop_long() {
            return drop_long;
        }

        public void setDrop_long(String drop_long) {
            this.drop_long = drop_long;
        }

        public String getDrop_location() {
            return drop_location;
        }

        public void setDrop_location(String drop_location) {
            this.drop_location = drop_location;
        }

        public String getRide_date() {
            return ride_date;
        }

        public void setRide_date(String ride_date) {
            this.ride_date = ride_date;
        }

        public String getRide_time() {
            return ride_time;
        }

        public void setRide_time(String ride_time) {
            this.ride_time = ride_time;
        }

        public String getLast_time_stamp() {
            return last_time_stamp;
        }

        public void setLast_time_stamp(String last_time_stamp) {
            this.last_time_stamp = last_time_stamp;
        }

        public String getRide_image() {
            return ride_image;
        }

        public void setRide_image(String ride_image) {
            this.ride_image = ride_image;
        }

        public String getLater_date() {
            return later_date;
        }

        public void setLater_date(String later_date) {
            this.later_date = later_date;
        }

        public String getLater_time() {
            return later_time;
        }

        public void setLater_time(String later_time) {
            this.later_time = later_time;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
        }

        public String getRide_type() {
            return ride_type;
        }

        public void setRide_type(String ride_type) {
            this.ride_type = ride_type;
        }

        public String getRide_status() {
            return ride_status;
        }

        public void setRide_status(String ride_status) {
            this.ride_status = ride_status;
        }

        public String getReason_id() {
            return reason_id;
        }

        public void setReason_id(String reason_id) {
            this.reason_id = reason_id;
        }

        public String getPayment_option_id() {
            return payment_option_id;
        }

        public void setPayment_option_id(String payment_option_id) {
            this.payment_option_id = payment_option_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getRide_admin_status() {
            return ride_admin_status;
        }

        public void setRide_admin_status(String ride_admin_status) {
            this.ride_admin_status = ride_admin_status;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(String referral_code) {
            this.referral_code = referral_code;
        }

        public String getFree_rides() {
            return free_rides;
        }

        public void setFree_rides(String free_rides) {
            this.free_rides = free_rides;
        }

        public String getReferral_code_send() {
            return referral_code_send;
        }

        public void setReferral_code_send(String referral_code_send) {
            this.referral_code_send = referral_code_send;
        }

        public String getPhone_verified() {
            return phone_verified;
        }

        public void setPhone_verified(String phone_verified) {
            this.phone_verified = phone_verified;
        }

        public String getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(String email_verified) {
            this.email_verified = email_verified;
        }

        public String getPassword_created() {
            return password_created;
        }

        public void setPassword_created(String password_created) {
            this.password_created = password_created;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getFacebook_mail() {
            return facebook_mail;
        }

        public void setFacebook_mail(String facebook_mail) {
            this.facebook_mail = facebook_mail;
        }

        public String getFacebook_image() {
            return facebook_image;
        }

        public void setFacebook_image(String facebook_image) {
            this.facebook_image = facebook_image;
        }

        public String getFacebook_firstname() {
            return facebook_firstname;
        }

        public void setFacebook_firstname(String facebook_firstname) {
            this.facebook_firstname = facebook_firstname;
        }

        public String getFacebook_lastname() {
            return facebook_lastname;
        }

        public void setFacebook_lastname(String facebook_lastname) {
            this.facebook_lastname = facebook_lastname;
        }

        public String getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(String google_id) {
            this.google_id = google_id;
        }

        public String getGoogle_name() {
            return google_name;
        }

        public void setGoogle_name(String google_name) {
            this.google_name = google_name;
        }

        public String getGoogle_mail() {
            return google_mail;
        }

        public void setGoogle_mail(String google_mail) {
            this.google_mail = google_mail;
        }

        public String getGoogle_image() {
            return google_image;
        }

        public void setGoogle_image(String google_image) {
            this.google_image = google_image;
        }

        public String getGoogle_token() {
            return google_token;
        }

        public void setGoogle_token(String google_token) {
            this.google_token = google_token;
        }

        public String getFacebook_token() {
            return facebook_token;
        }

        public void setFacebook_token(String facebook_token) {
            this.facebook_token = facebook_token;
        }

        public String getToken_created() {
            return token_created;
        }

        public void setToken_created(String token_created) {
            this.token_created = token_created;
        }

        public String getLogin_logout() {
            return login_logout;
        }

        public void setLogin_logout(String login_logout) {
            this.login_logout = login_logout;
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
}
