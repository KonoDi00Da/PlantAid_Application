package com.example.plantaid_application.Models;

public class PlantListModel {
    String commonName, sciName;
    String image;

    PlantListModel() {

    }

    public PlantListModel(String commonName, String sciName, String image) {
        this.commonName = commonName;
        this.sciName = sciName;
        this.image = image;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}