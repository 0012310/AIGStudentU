package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/29/2018.
 */

public class ProjectDatum {
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("p_start_date")
    @Expose
    private String pStartDate;
    @SerializedName("project_type")
    @Expose
    private String projectType;
    @SerializedName("trainer_name")
    @Expose
    private String trainerName;
    @SerializedName("filename")
    @Expose
    private String filename;
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

    public String getPStartDate() {
        return pStartDate;
    }

    public void setPStartDate(String pStartDate) {
        this.pStartDate = pStartDate;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
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
