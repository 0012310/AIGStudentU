package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/26/2018.
 */

public class ViewIncidentData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("incident_data")
    @Expose
    private List<IncidentDatum> incidentData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<IncidentDatum> getIncidentData() {
        return incidentData;
    }

    public void setIncidentData(List<IncidentDatum> incidentData) {
        this.incidentData = incidentData;
    }
}
