package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/19/2018.
 */

public class DueFeeDetail {
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("startup_pack")
    @Expose
    private String startupPack;
    @SerializedName("url")
    @Expose
    private String url;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartupPack() {
        return startupPack;
    }

    public void setStartupPack(String startupPack) {
        this.startupPack = startupPack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
