package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/4/2018.
 */

public class GraduationData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("education_details")
    @Expose
    private List<EducationDetail> educationDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EducationDetail> getEducationDetails() {
        return educationDetails;
    }

    public void setEducationDetails(List<EducationDetail> educationDetails) {
        this.educationDetails = educationDetails;
    }
}
