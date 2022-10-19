package com.example.plantaid_application.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("organ")
    @Expose
    private String organ;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("citation")
    @Expose
    private String citation;

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
}
