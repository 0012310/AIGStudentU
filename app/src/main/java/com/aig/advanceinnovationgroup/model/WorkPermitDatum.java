package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/3/2018.
 */

public class WorkPermitDatum {
    @SerializedName("work_permit")
    @Expose
    private String workPermit;

    public String getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(String workPermit) {
        this.workPermit = workPermit;
    }

}
