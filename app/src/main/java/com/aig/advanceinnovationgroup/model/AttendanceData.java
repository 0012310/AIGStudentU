package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class AttendanceData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("attendance_detail")
    @Expose
    private List<AttendanceDetail> attendanceDetail = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AttendanceDetail> getAttendanceDetail() {
        return attendanceDetail;
    }

    public void setAttendanceDetail(List<AttendanceDetail> attendanceDetail) {
        this.attendanceDetail = attendanceDetail;
    }
}
