package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class DesignationData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("designation_data")
    @Expose
    private List<DesignationDatum> designationData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DesignationDatum> getDesignationData() {
        return designationData;
    }

    public void setDesignationData(List<DesignationDatum> designationData) {
        this.designationData = designationData;
    }

}
