package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/3/2018.
 */

public class WorkPermitData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("work_permit_data")
    @Expose
    private List<WorkPermitDatum> workPermitData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<WorkPermitDatum> getWorkPermitData() {
        return workPermitData;
    }

    public void setWorkPermitData(List<WorkPermitDatum> workPermitData) {
        this.workPermitData = workPermitData;
    }

}
