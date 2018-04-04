package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/4/2018.
 */

public class LanguageDetail {

    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("proficiency")
    @Expose
    private String proficiency;
    @SerializedName("read")
    @Expose
    private String read;
    @SerializedName("write")
    @Expose
    private String write;
    @SerializedName("speak")
    @Expose
    private String speak;

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }
}
