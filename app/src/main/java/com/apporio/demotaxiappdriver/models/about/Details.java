
package com.apporio.demotaxiappdriver.models.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("page_id")
    @Expose
    private String pageId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title_arabic")
    @Expose
    private String titleArabic;
    @SerializedName("description_arabic")
    @Expose
    private String descriptionArabic;
    @SerializedName("title_french")
    @Expose
    private String titleFrench;
    @SerializedName("description_french")
    @Expose
    private String descriptionFrench;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleArabic() {
        return titleArabic;
    }

    public void setTitleArabic(String titleArabic) {
        this.titleArabic = titleArabic;
    }

    public String getDescriptionArabic() {
        return descriptionArabic;
    }

    public void setDescriptionArabic(String descriptionArabic) {
        this.descriptionArabic = descriptionArabic;
    }

    public String getTitleFrench() {
        return titleFrench;
    }

    public void setTitleFrench(String titleFrench) {
        this.titleFrench = titleFrench;
    }

    public String getDescriptionFrench() {
        return descriptionFrench;
    }

    public void setDescriptionFrench(String descriptionFrench) {
        this.descriptionFrench = descriptionFrench;
    }

}
