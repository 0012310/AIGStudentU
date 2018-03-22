package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/22/2018.
 */

public class ProjectData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("project_view")
    @Expose
    private List<ProjectView> projectView = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectView> getProjectView() {
        return projectView;
    }

    public void setProjectView(List<ProjectView> projectView) {
        this.projectView = projectView;
    }
}
