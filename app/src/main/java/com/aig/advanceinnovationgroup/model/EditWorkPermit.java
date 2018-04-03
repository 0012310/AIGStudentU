package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/3/2018.
 */

public class EditWorkPermit {

    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("employment_type")
    @Expose
    private String employmentType;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("physically")
    @Expose
    private String physically;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("work_permit")
    @Expose
    private String workPermit;
    @SerializedName("countries")
    @Expose
    private String countries;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhysically() {
        return physically;
    }

    public void setPhysically(String physically) {
        this.physically = physically;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(String workPermit) {
        this.workPermit = workPermit;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

}
