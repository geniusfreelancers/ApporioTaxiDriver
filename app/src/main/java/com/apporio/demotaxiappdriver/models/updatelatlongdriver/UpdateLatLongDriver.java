
package com.apporio.demotaxiappdriver.models.updatelatlongdriver;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateLatLongDriver {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("record")
    @Expose
    private Record record;

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
     *     The record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * 
     * @param record
     *     The record
     */
    public void setRecord(Record record) {
        this.record = record;
    }

}
