package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/2/2018.
 */

public class ProjectDetailData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("project_list_data")
    @Expose
    private List<ProjectListDatum> projectListData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectListDatum> getProjectListData() {
        return projectListData;
    }

    public void setProjectListData(List<ProjectListDatum> projectListData) {
        this.projectListData = projectListData;
    }
}
