package com.example.plantaid_application.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genus {

    @SerializedName("scientificNameWithoutAuthor")
    @Expose
    private String scientificNameWithoutAuthor;
    @SerializedName("scientificNameAuthorship")
    @Expose
    private String scientificNameAuthorship;
    @SerializedName("scientificName")
    @Expose
    private String scientificName;

    public String getScientificNameWithoutAuthor() {
        return scientificNameWithoutAuthor;
    }

    public void setScientificNameWithoutAuthor(String scientificNameWithoutAuthor) {
        this.scientificNameWithoutAuthor = scientificNameWithoutAuthor;
    }

    public String getScientificNameAuthorship() {
        return scientificNameAuthorship;
    }

    public void setScientificNameAuthorship(String scientificNameAuthorship) {
        this.scientificNameAuthorship = scientificNameAuthorship;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
}
