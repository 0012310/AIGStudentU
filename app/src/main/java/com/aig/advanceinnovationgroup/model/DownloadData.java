package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/19/2018.
 */

public class DownloadData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("due_fee_details")
    @Expose
    private List<DueFeeDetail> dueFeeDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DueFeeDetail> getDueFeeDetails() {
        return dueFeeDetails;
    }

    public void setDueFeeDetails(List<DueFeeDetail> dueFeeDetails) {
        this.dueFeeDetails = dueFeeDetails;
    }
}
