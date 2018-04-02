package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/2/2018.
 */

public class ViewSkillsDatum {

    @SerializedName("skill_id")
    @Expose
    private String skillId;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("version_used")
    @Expose
    private String versionUsed;
    @SerializedName("last_used")
    @Expose
    private String lastUsed;
    @SerializedName("exp_year")
    @Expose
    private String expYear;
    @SerializedName("exp_month")
    @Expose
    private String expMonth;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getVersionUsed() {
        return versionUsed;
    }

    public void setVersionUsed(String versionUsed) {
        this.versionUsed = versionUsed;
    }

    public String getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }
}
