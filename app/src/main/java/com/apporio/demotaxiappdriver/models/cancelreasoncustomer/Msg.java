
package com.apporio.demotaxiappdriver.models.cancelreasoncustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("reason_id")
    @Expose
    private String reasonId;
    @SerializedName("reason_name")
    @Expose
    private String reasonName;
    @SerializedName("reason_type")
    @Expose
    private String reasonType;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("reason_name_arabic")
    @Expose
    private String reasonNameArabic;

    @SerializedName("reason_name_french")
    @Expose
    private String reasonNameFrench;

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getReasonNameArabic() {
        return reasonNameArabic;
    }

    public void setReasonNameArabic(String reasonNameArabic) {
        this.reasonNameArabic = reasonNameArabic;
    }

    public String getReasonNameFrench() {
        return reasonNameFrench;
    }

    public void setReasonNameFrench(String reasonNameFrench) {
        this.reasonNameFrench = reasonNameFrench;
    }
}
