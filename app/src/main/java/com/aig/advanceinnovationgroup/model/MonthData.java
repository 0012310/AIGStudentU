package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class MonthData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("month_name")
    @Expose
    private List<MonthName> monthName = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MonthName> getMonthName() {
        return monthName;
    }

    public void setMonthName(List<MonthName> monthName) {
        this.monthName = monthName;
    }
}
