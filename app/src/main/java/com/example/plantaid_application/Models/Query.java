package com.example.plantaid_application.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query {

    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("organs")
    @Expose
    private List<String> organs = null;
    @SerializedName("includeRelatedImages")
    @Expose
    private Boolean includeRelatedImages;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getOrgans() {
        return organs;
    }

    public void setOrgans(List<String> organs) {
        this.organs = organs;
    }

    public Boolean getIncludeRelatedImages() {
        return includeRelatedImages;
    }

    public void setIncludeRelatedImages(Boolean includeRelatedImages) {
        this.includeRelatedImages = includeRelatedImages;
    }
}
