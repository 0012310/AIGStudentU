package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/4/2018.
 */

public class LanguageData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("language_details")
    @Expose
    private List<LanguageDetail> languageDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<LanguageDetail> getLanguageDetails() {
        return languageDetails;
    }

    public void setLanguageDetails(List<LanguageDetail> languageDetails) {
        this.languageDetails = languageDetails;
    }

}
