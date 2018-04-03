package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/3/2018.
 */

public class EditDesiredData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("edit_work_permit")
    @Expose
    private List<EditWorkPermit> editWorkPermit = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EditWorkPermit> getEditWorkPermit() {
        return editWorkPermit;
    }

    public void setEditWorkPermit(List<EditWorkPermit> editWorkPermit) {
        this.editWorkPermit = editWorkPermit;
    }
}
