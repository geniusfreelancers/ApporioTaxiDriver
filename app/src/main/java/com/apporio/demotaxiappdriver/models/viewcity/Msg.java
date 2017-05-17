
package com.apporio.demotaxiappdriver.models.viewcity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_name_arabic")
    @Expose
    private String cityNameArabic;
    @SerializedName("city_name_french")
    @Expose
    private String cityNameFrench;
    @SerializedName("city_admin_status")
    @Expose
    private String status;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameArabic() {
        return cityNameArabic;
    }

    public void setCityNameArabic(String cityNameArabic) {
        this.cityNameArabic = cityNameArabic;
    }

    public String getCityNameFrench() {
        return cityNameFrench;
    }

    public void setCityNameFrench(String cityNameFrench) {
        this.cityNameFrench = cityNameFrench;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
