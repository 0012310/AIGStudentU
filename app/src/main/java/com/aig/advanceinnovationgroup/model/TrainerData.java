package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/26/2018.
 */

public class TrainerData {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("trainer_data")
    @Expose
    private List<TrainerDatum> trainerData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TrainerDatum> getTrainerData() {
        return trainerData;
    }

    public void setTrainerData(List<TrainerDatum> trainerData) {
        this.trainerData = trainerData;
    }
}
