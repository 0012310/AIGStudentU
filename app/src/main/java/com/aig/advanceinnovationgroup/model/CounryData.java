package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 4/3/2018.
 */

public class CounryData {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("country_list")
    @Expose
    private List<CountryList> countryList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CountryList> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryList> countryList) {
        this.countryList = countryList;
    }
}
