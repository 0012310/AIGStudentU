package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/29/2018.
 */

public class ProjectDetail {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("project_data")
    @Expose
    private List<ProjectDatum> projectData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectDatum> getProjectData() {
        return projectData;
    }

    public void setProjectData(List<ProjectDatum> projectData) {
        this.projectData = projectData;
    }
}
