package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/9/2018.
 */

public class ExamDetail {


    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("Exstatus")
    @Expose
    private String exstatus;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("exam_title")
    @Expose
    private String examTitle;
    @SerializedName("exam_date")
    @Expose
    private String examDate;
    @SerializedName("no_of_question")
    @Expose
    private String noOfQuestion;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_questions")
    @Expose
    private String totalQuestions;
    @SerializedName("Attempted")
    @Expose
    private Integer attempted;
    @SerializedName("Correct")
    @Expose
    private Integer correct;
    @SerializedName("Score_total")
    @Expose
    private String scoreTotal;
    @SerializedName("Score_attempted")
    @Expose
    private String scoreAttempted;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExstatus() {
        return exstatus;
    }

    public void setExstatus(String exstatus) {
        this.exstatus = exstatus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(String noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getAttempted() {
        return attempted;
    }

    public void setAttempted(Integer attempted) {
        this.attempted = attempted;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public String getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(String scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getScoreAttempted() {
        return scoreAttempted;
    }

    public void setScoreAttempted(String scoreAttempted) {
        this.scoreAttempted = scoreAttempted;
    }
}
