
package com.apporio.demotaxiappdriver.models.document_type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("document_type_id")
    @Expose
    private String documentTypeId;
    @SerializedName("document_name")
    @Expose
    private String documentName;
    @SerializedName("merchant_id")
    @Expose
    private String merchantId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(String documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
