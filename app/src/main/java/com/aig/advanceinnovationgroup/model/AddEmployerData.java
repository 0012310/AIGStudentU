package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class AddEmployerData {

    @Expose
    private Integer status;
    @SerializedName("employer_data")
    @Expose
    private List<EmployerDatum> employerData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EmployerDatum> getEmployerData() {
        return employerData;
    }

    public void setEmployerData(List<EmployerDatum> employerData) {
        this.employerData = employerData;
    }
}
