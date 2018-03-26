package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/26/2018.
 */

public class IncidentDatum {

    @SerializedName("incident_subject")
    @Expose
    private String incidentSubject;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("class_date")
    @Expose
    private String classDate;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("no_of_days")
    @Expose
    private String noOfDays;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("incident_id")
    @Expose
    private String incidentId;
    @SerializedName("incident_status")
    @Expose
    private String incidentStatus;

    public String getIncidentSubject() {
        return incidentSubject;
    }

    public void setIncidentSubject(String incidentSubject) {
        this.incidentSubject = incidentSubject;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

}
