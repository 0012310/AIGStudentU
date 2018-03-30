package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class YearData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("year_name")
    @Expose
    private List<YearName> yearName = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<YearName> getYearName() {
        return yearName;
    }

    public void setYearName(List<YearName> yearName) {
        this.yearName = yearName;
    }
}
