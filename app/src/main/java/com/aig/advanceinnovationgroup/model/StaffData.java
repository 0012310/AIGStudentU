package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/24/2018.
 */

public class StaffData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("staff_details")
    @Expose
    private List<StaffDetail> staffDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<StaffDetail> getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(List<StaffDetail> staffDetails) {
        this.staffDetails = staffDetails;
    }
}
