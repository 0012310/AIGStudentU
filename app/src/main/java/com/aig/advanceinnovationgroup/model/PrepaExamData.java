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
    @SerializedName("add_exam")
    @Expose
    private List<ExamDetail> addExam = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExamDetail> getAddExam() {
        return addExam;
    }

    public void setAddExam(List<ExamDetail> addExam) {
        this.addExam = addExam;
    }
}
