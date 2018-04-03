package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/3/2018.
 */

public class DesiredData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("desired_job")
    @Expose
    private List<DesiredJob> desiredJob = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DesiredJob> getDesiredJob() {
        return desiredJob;
    }

    public void setDesiredJob(List<DesiredJob> desiredJob) {
        this.desiredJob = desiredJob;
    }
}
