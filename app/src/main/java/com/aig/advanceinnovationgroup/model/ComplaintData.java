package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/7/2018.
 */

public class ComplaintData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("complaint_type")
    @Expose
    private List<ComplaintType> complaintType = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ComplaintType> getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(List<ComplaintType> complaintType) {
        this.complaintType = complaintType;
    }
}
