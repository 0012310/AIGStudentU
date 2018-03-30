package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/30/2018.
 */

public class EmployerDatum {


    @SerializedName("employer_id")
    @Expose
    private String employerId;
    @SerializedName("employer_name")
    @Expose
    private String employerName;
    @SerializedName("employer_type")
    @Expose
    private String employerType;
    @SerializedName("from_month")
    @Expose
    private String fromMonth;
    @SerializedName("from_year")
    @Expose
    private String fromYear;
    @SerializedName("to_month")
    @Expose
    private String toMonth;
    @SerializedName("to_year")
    @Expose
    private String toYear;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("job_profile")
    @Expose
    private String jobProfile;

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerType() {
        return employerType;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(String jobProfile) {
        this.jobProfile = jobProfile;
    }
}
