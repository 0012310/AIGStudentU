package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/4/2018.
 */

public class EducationDetail {

    @SerializedName("education_id")
    @Expose
    private String educationId;
    @SerializedName("basic")
    @Expose
    private String basic;
    @SerializedName("basic_type")
    @Expose
    private String basicType;
    @SerializedName("basic_specialization")
    @Expose
    private String basicSpecialization;
    @SerializedName("basic_university")
    @Expose
    private String basicUniversity;
    @SerializedName("basic_year")
    @Expose
    private String basicYear;
    @SerializedName("pg_type")
    @Expose
    private String pgType;
    @SerializedName("pg")
    @Expose
    private String pg;
    @SerializedName("pg_specialization")
    @Expose
    private String pgSpecialization;
    @SerializedName("pg_university")
    @Expose
    private String pgUniversity;
    @SerializedName("pg_year")
    @Expose
    private String pgYear;
    @SerializedName("doct_type")
    @Expose
    private String doctType;
    @SerializedName("doctorate")
    @Expose
    private String doctorate;
    @SerializedName("doct_specialization")
    @Expose
    private String doctSpecialization;
    @SerializedName("doct_university")
    @Expose
    private String doctUniversity;
    @SerializedName("doct_year")
    @Expose
    private String doctYear;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getBasicType() {
        return basicType;
    }

    public void setBasicType(String basicType) {
        this.basicType = basicType;
    }

    public String getBasicSpecialization() {
        return basicSpecialization;
    }

    public void setBasicSpecialization(String basicSpecialization) {
        this.basicSpecialization = basicSpecialization;
    }

    public String getBasicUniversity() {
        return basicUniversity;
    }

    public void setBasicUniversity(String basicUniversity) {
        this.basicUniversity = basicUniversity;
    }

    public String getBasicYear() {
        return basicYear;
    }

    public void setBasicYear(String basicYear) {
        this.basicYear = basicYear;
    }

    public String getPgType() {
        return pgType;
    }

    public void setPgType(String pgType) {
        this.pgType = pgType;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getPgSpecialization() {
        return pgSpecialization;
    }

    public void setPgSpecialization(String pgSpecialization) {
        this.pgSpecialization = pgSpecialization;
    }

    public String getPgUniversity() {
        return pgUniversity;
    }

    public void setPgUniversity(String pgUniversity) {
        this.pgUniversity = pgUniversity;
    }

    public String getPgYear() {
        return pgYear;
    }

    public void setPgYear(String pgYear) {
        this.pgYear = pgYear;
    }

    public String getDoctType() {
        return doctType;
    }

    public void setDoctType(String doctType) {
        this.doctType = doctType;
    }

    public String getDoctorate() {
        return doctorate;
    }

    public void setDoctorate(String doctorate) {
        this.doctorate = doctorate;
    }

    public String getDoctSpecialization() {
        return doctSpecialization;
    }

    public void setDoctSpecialization(String doctSpecialization) {
        this.doctSpecialization = doctSpecialization;
    }

    public String getDoctUniversity() {
        return doctUniversity;
    }

    public void setDoctUniversity(String doctUniversity) {
        this.doctUniversity = doctUniversity;
    }

    public String getDoctYear() {
        return doctYear;
    }

    public void setDoctYear(String doctYear) {
        this.doctYear = doctYear;
    }

}
