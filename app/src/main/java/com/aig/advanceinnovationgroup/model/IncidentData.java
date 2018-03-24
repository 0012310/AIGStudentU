package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/24/2018.
 */

public class IncidentData {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("incident_subject")
    @Expose
    private List<IncidentSubject> incidentSubject = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<IncidentSubject> getIncidentSubject() {
        return incidentSubject;
    }

    public void setIncidentSubject(List<IncidentSubject> incidentSubject) {
        this.incidentSubject = incidentSubject;
    }
}
