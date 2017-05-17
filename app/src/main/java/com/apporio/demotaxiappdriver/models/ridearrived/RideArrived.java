
package com.apporio.demotaxiappdriver.models.ridearrived;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RideArrived {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("details")
    @Expose
    private Details details;

    /**
     * 
     * @return
     *     The result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The result
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The details
     */
    public Details getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(Details details) {
        this.details = details;
    }

}
