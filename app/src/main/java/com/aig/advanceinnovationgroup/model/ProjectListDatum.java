package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/2/2018.
 */

public class ProjectListDatum {

    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("project_title")
    @Expose
    private String projectTitle;
    @SerializedName("project_type")
    @Expose
    private String projectType;
    @SerializedName("project_details")
    @Expose
    private String projectDetails;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("role_description")
    @Expose
    private String roleDescription;
    @SerializedName("team_size")
    @Expose
    private String teamSize;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
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

}
