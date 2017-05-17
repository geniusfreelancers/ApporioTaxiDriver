
package com.apporio.demotaxiappdriver.models.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("details")
    @Expose
    private Details details;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

}
