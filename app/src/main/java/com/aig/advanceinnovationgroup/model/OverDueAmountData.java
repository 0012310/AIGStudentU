package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class OverDueAmountData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("overdue_fee_details")
    @Expose
    private List<OverdueFeeDetail> overdueFeeDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OverdueFeeDetail> getOverdueFeeDetails() {
        return overdueFeeDetails;
    }

    public void setOverdueFeeDetails(List<OverdueFeeDetail> overdueFeeDetails) {
        this.overdueFeeDetails = overdueFeeDetails;
    }

}
