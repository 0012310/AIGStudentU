package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/26/2018.
 */

public class TimeData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("dropdown_value")
    @Expose
    private List<DropdownValue> dropdownValue = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DropdownValue> getDropdownValue() {
        return dropdownValue;
    }

    public void setDropdownValue(List<DropdownValue> dropdownValue) {
        this.dropdownValue = dropdownValue;
    }
}
