
package com.apporio.demotaxiappdriver.models.viewrides;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewRides {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("msg")
    @Expose
    private List<Msg> msg = null;

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
    public List<Msg> getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    public void setMsg(List<Msg> msg) {
        this.msg = msg;
    }

}
