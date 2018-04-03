package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/3/2018.
 */

public class DesiredJob {


    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("permanent")
    @Expose
    private String permanent;
    @SerializedName("employment_type")
    @Expose
    private String employmentType;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("phy_challanged")
    @Expose
    private String phyChallanged;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("work_permit")
    @Expose
    private String workPermit;
    @SerializedName("other_countries")
    @Expose
    private String otherCountries;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPermanent() {
        return permanent;
    }

    public void setPermanent(String permanent) {
        this.permanent = permanent;
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

    public String getPhyChallanged() {
        return phyChallanged;
    }

    public void setPhyChallanged(String phyChallanged) {
        this.phyChallanged = phyChallanged;
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

    public String getOtherCountries() {
        return otherCountries;
    }

    public void setOtherCountries(String otherCountries) {
        this.otherCountries = otherCountries;
    }

}
