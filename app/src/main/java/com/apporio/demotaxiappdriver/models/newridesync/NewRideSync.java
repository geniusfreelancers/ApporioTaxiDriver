
package com.apporio.demotaxiappdriver.models.newridesync;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewRideSync {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("details")
    @Expose
    private Details details;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

}
