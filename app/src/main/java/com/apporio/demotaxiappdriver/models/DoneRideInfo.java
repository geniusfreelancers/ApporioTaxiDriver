
package com.apporio.demotaxiappdriver.models;

public class DoneRideInfo {


    /**
     * result : 1
     * msg : {"done_ride_id":"270","begin_location":"1102, Sohna Road,Block S, Sispal Vihar, Sector 47,Gurugram, Haryana 122004,","end_location":"Not yet fetched","waiting_time":"0 Min","waiting_price":"0","ride_time":"0 Min","ride_time_price":"0","distance":"0","amount":"50","tot_time":"0","username":"samir goel .","user_phone":"+919650923089","user_image":"","driver_id":"100","driver_name":"lsllsl","driver_image":"","payment_option_id":"1","payment_option_name":"Cash","ride_id":"653","ride_date":"Tuesday, May 16","car_type_id":"2","car_type_name":"HATCHBACK","car_type_image":"uploads/car/car_4.png","coupons_code":"","coupons_price":"","total_amount":50}
     */

    private int result;
    private MsgBean msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * done_ride_id : 270
         * begin_location : 1102, Sohna Road,Block S, Sispal Vihar, Sector 47,Gurugram, Haryana 122004,
         * end_location : Not yet fetched
         * waiting_time : 0 Min
         * waiting_price : 0
         * ride_time : 0 Min
         * ride_time_price : 0
         * distance : 0
         * amount : 50
         * tot_time : 0
         * username : samir goel .
         * user_phone : +919650923089
         * user_image :
         * driver_id : 100
         * driver_name : lsllsl
         * driver_image :
         * payment_option_id : 1
         * payment_option_name : Cash
         * ride_id : 653
         * ride_date : Tuesday, May 16
         * car_type_id : 2
         * car_type_name : HATCHBACK
         * car_type_image : uploads/car/car_4.png
         * coupons_code :
         * coupons_price :
         * total_amount : 50
         */

        private String done_ride_id;
        private String begin_location;
        private String end_location;
        private String waiting_time;
        private String waiting_price;
        private String ride_time;
        private String ride_time_price;
        private String distance;
        private String amount;
        private String tot_time;
        private String username;
        private String user_phone;
        private String user_image;
        private String driver_id;
        private String driver_name;
        private String driver_image;
        private String payment_option_id;
        private String payment_option_name;
        private String ride_id;
        private String ride_date;
        private String car_type_id;
        private String car_type_name;
        private String car_type_image;
        private String coupons_code;
        private String coupons_price;
        private int total_amount;

        public String getDone_ride_id() {
            return done_ride_id;
        }

        public void setDone_ride_id(String done_ride_id) {
            this.done_ride_id = done_ride_id;
        }

        public String getBegin_location() {
            return begin_location;
        }

        public void setBegin_location(String begin_location) {
            this.begin_location = begin_location;
        }

        public String getEnd_location() {
            return end_location;
        }

        public void setEnd_location(String end_location) {
            this.end_location = end_location;
        }

        public String getWaiting_time() {
            return waiting_time;
        }

        public void setWaiting_time(String waiting_time) {
            this.waiting_time = waiting_time;
        }

        public String getWaiting_price() {
            return waiting_price;
        }

        public void setWaiting_price(String waiting_price) {
            this.waiting_price = waiting_price;
        }

        public String getRide_time() {
            return ride_time;
        }

        public void setRide_time(String ride_time) {
            this.ride_time = ride_time;
        }

        public String getRide_time_price() {
            return ride_time_price;
        }

        public void setRide_time_price(String ride_time_price) {
            this.ride_time_price = ride_time_price;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTot_time() {
            return tot_time;
        }

        public void setTot_time(String tot_time) {
            this.tot_time = tot_time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_image() {
            return user_image;
        }

        public void setUser_image(String user_image) {
            this.user_image = user_image;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_image() {
            return driver_image;
        }

        public void setDriver_image(String driver_image) {
            this.driver_image = driver_image;
        }

        public String getPayment_option_id() {
            return payment_option_id;
        }

        public void setPayment_option_id(String payment_option_id) {
            this.payment_option_id = payment_option_id;
        }

        public String getPayment_option_name() {
            return payment_option_name;
        }

        public void setPayment_option_name(String payment_option_name) {
            this.payment_option_name = payment_option_name;
        }

        public String getRide_id() {
            return ride_id;
        }

        public void setRide_id(String ride_id) {
            this.ride_id = ride_id;
        }

        public String getRide_date() {
            return ride_date;
        }

        public void setRide_date(String ride_date) {
            this.ride_date = ride_date;
        }

        public String getCar_type_id() {
            return car_type_id;
        }

        public void setCar_type_id(String car_type_id) {
            this.car_type_id = car_type_id;
        }

        public String getCar_type_name() {
            return car_type_name;
        }

        public void setCar_type_name(String car_type_name) {
            this.car_type_name = car_type_name;
        }

        public String getCar_type_image() {
            return car_type_image;
        }

        public void setCar_type_image(String car_type_image) {
            this.car_type_image = car_type_image;
        }

        public String getCoupons_code() {
            return coupons_code;
        }

        public void setCoupons_code(String coupons_code) {
            this.coupons_code = coupons_code;
        }

        public String getCoupons_price() {
            return coupons_price;
        }

        public void setCoupons_price(String coupons_price) {
            this.coupons_price = coupons_price;
        }

        public int getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }
    }
}
