package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/22/2018.
 */

public class ProjectTypeData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("project_type")
    @Expose
    private List<ProjectType> projectType = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectType> getProjectType() {
        return projectType;
    }

    public void setProjectType(List<ProjectType> projectType) {
        this.projectType = projectType;
    }
}
