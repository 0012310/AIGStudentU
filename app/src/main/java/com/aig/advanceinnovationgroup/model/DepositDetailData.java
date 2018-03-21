package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class DepositDetailData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("deposit_fee_details")
    @Expose
    private List<DepositFeeDetail> depositFeeDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DepositFeeDetail> getDepositFeeDetails() {
        return depositFeeDetails;
    }

    public void setDepositFeeDetails(List<DepositFeeDetail> depositFeeDetails) {
        this.depositFeeDetails = depositFeeDetails;
    }
}
