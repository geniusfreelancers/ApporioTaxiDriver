
package com.apporio.demotaxiappdriver.models.carmodels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarModels {

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
