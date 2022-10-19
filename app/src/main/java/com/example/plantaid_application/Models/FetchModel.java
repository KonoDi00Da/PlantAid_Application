package com.example.plantaid_application.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchModel {
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("string")
    @Expose
    private String string;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("preferedReferential")
    @Expose
    private String preferedReferential;
    @SerializedName("bestMatch")
    @Expose
    private String bestMatch;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("remainingIdentificationRequests")
    @Expose
    private Integer remainingIdentificationRequests;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreferedReferential() {
        return preferedReferential;
    }

    public void setPreferedReferential(String preferedReferential) {
        this.preferedReferential = preferedReferential;
    }

    public String getBestMatch() {
        return bestMatch;
    }

    public void setBestMatch(String bestMatch) {
        this.bestMatch = bestMatch;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getRemainingIdentificationRequests() {
        return remainingIdentificationRequests;
    }

    public void setRemainingIdentificationRequests(Integer remainingIdentificationRequests) {
        this.remainingIdentificationRequests = remainingIdentificationRequests;
    }

    public static class Result {
        @SerializedName("species")
        @Expose
        private Species species;
        @SerializedName("images")
        @Expose
        private List<Image> images = null;

        public Species getSpecies() {
            return species;
        }

        public void setSpecies(Species species) {
            this.species = species;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }


    }
}
