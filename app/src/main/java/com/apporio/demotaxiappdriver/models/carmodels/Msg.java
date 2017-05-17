
package com.apporio.demotaxiappdriver.models.carmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("car_model_id")
    @Expose
    private String carModelId;
    @SerializedName("car_model_name")
    @Expose
    private String carModelName;
    @SerializedName("car_model_image")
    @Expose
    private String carModelImage;
    @SerializedName("car_type_id")
    @Expose
    private String carTypeId;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("car_model_name_arabic")
    @Expose
    private String carModelNameArabic;

    @SerializedName("car_model_name_french")
    @Expose
    private String carModelNameFrench;


    /**
     * @return The carModelId
     */
    public String getCarModelId() {
        return carModelId;
    }

    /**
     * @param carModelId The car_model_id
     */
    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    /**
     * @return The carModelName
     */
    public String getCarModelName() {
        return carModelName;
    }

    /**
     * @param carModelName The car_model_name
     */
    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    /**
     * @return The carModelImage
     */
    public String getCarModelImage() {
        return carModelImage;
    }

    /**
     * @param carModelImage The car_model_image
     */
    public void setCarModelImage(String carModelImage) {
        this.carModelImage = carModelImage;
    }

    /**
     * @return The carTypeId
     */
    public String getCarTypeId() {
        return carTypeId;
    }

    /**
     * @param carTypeId The car_type_id
     */
    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarModelNameArabic() {
        return carModelNameArabic;
    }

    public void setCarModelNameArabic(String carModelNameArabic) {
        this.carModelNameArabic = carModelNameArabic;
    }

    public String getCarModelNameFrench() {
        return carModelNameFrench;
    }

    public void setCarModelNameFrench(String carModelNameFrench) {
        this.carModelNameFrench = carModelNameFrench;
    }
}
