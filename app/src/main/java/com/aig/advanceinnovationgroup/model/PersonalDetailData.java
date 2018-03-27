package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/27/2018.
 */

public class PersonalDetailData {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("profile_detail")
    @Expose
    private List<ProfileDetail> profileDetail = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProfileDetail> getProfileDetail() {
        return profileDetail;
    }

    public void setProfileDetail(List<ProfileDetail> profileDetail) {
        this.profileDetail = profileDetail;
    }
}
