package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/9/2018.
 */

public class PrepaExamData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("exam_details")
    @Expose
    private List<ExamDetail> examDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExamDetail> getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(List<ExamDetail> examDetails) {
        this.examDetails = examDetails;
    }
}
