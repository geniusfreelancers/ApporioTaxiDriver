
package com.apporio.demotaxiappdriver.models.viewcartype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("car_type_id")
    @Expose
    private String carTypeId;
    @SerializedName("car_type_name")
    @Expose
    private String carTypeName;
    @SerializedName("car_name_arabic")
    @Expose
    private String carNameArabic;
    @SerializedName("car_type_name_french")
    @Expose
    private String carTypeNameFrench;
    @SerializedName("car_type_image")
    @Expose
    private String carTypeImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("city_id")
    @Expose
    private String cityId;

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarNameArabic() {
        return carNameArabic;
    }

    public void setCarNameArabic(String carNameArabic) {
        this.carNameArabic = carNameArabic;
    }

    public String getCarTypeNameFrench() {
        return carTypeNameFrench;
    }

    public void setCarTypeNameFrench(String carTypeNameFrench) {
        this.carTypeNameFrench = carTypeNameFrench;
    }

    public String getCarTypeImage() {
        return carTypeImage;
    }

    public void setCarTypeImage(String carTypeImage) {
        this.carTypeImage = carTypeImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
