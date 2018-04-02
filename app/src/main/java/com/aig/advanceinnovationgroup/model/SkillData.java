package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/2/2018.
 */

public class SkillData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("view_skills_data")
    @Expose
    private List<ViewSkillsDatum> viewSkillsData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ViewSkillsDatum> getViewSkillsData() {
        return viewSkillsData;
    }

    public void setViewSkillsData(List<ViewSkillsDatum> viewSkillsData) {
        this.viewSkillsData = viewSkillsData;
    }
}
