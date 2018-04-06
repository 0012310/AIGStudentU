package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/5/2018.
 */

public class FaqData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("faqs_data")
    @Expose
    private List<FaqsDatum> faqsData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<FaqsDatum> getFaqsData() {
        return faqsData;
    }

    public void setFaqsData(List<FaqsDatum> faqsData) {
        this.faqsData = faqsData;
    }
}
