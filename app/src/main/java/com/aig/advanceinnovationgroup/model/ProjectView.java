package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/22/2018.
 */

public class ProjectView {

    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("pstatus_val")
    @Expose
    private Object pstatusVal;
    @SerializedName("pstatus")
    @Expose
    private String pstatus;
    @SerializedName("p_start_date")
    @Expose
    private String pStartDate;
    @SerializedName("p_close_date")
    @Expose
    private String pCloseDate;
    @SerializedName("project_type")
    @Expose
    private String projectType;
    @SerializedName("trainer_name")
    @Expose
    private String trainerName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("filename")
    @Expose
    private Object filename;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("project_status")
    @Expose
    private String projectStatus;
    @SerializedName("download_url")
    @Expose
    private String downloadUrl;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Object getPstatusVal() {
        return pstatusVal;
    }

    public void setPstatusVal(Object pstatusVal) {
        this.pstatusVal = pstatusVal;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getPStartDate() {
        return pStartDate;
    }

    public void setPStartDate(String pStartDate) {
        this.pStartDate = pStartDate;
    }

    public String getPCloseDate() {
        return pCloseDate;
    }

    public void setPCloseDate(String pCloseDate) {
        this.pCloseDate = pCloseDate;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getFilename() {
        return filename;
    }

    public void setFilename(Object filename) {
        this.filename = filename;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
