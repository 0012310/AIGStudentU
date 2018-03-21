package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/21/2018.
 */

public class AttendanceDetail {
    @SerializedName("class_date")
    @Expose
    private String classDate;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("trainer_name")
    @Expose
    private String trainerName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("course_topic_content_id")
    @Expose
    private String courseTopicContentId;
    @SerializedName("attandance_status")
    @Expose
    private String attandanceStatus;
    @SerializedName("course_content")
    @Expose
    private String courseContent;

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseTopicContentId() {
        return courseTopicContentId;
    }

    public void setCourseTopicContentId(String courseTopicContentId) {
        this.courseTopicContentId = courseTopicContentId;
    }

    public String getAttandanceStatus() {
        return attandanceStatus;
    }

    public void setAttandanceStatus(String attandanceStatus) {
        this.attandanceStatus = attandanceStatus;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

}
