package com.example.plantaid_application.Models;

public class PlantModel {
    String commonName, sciName, description, care, harvest, pestsDiseases, varieties,
    water, ytLink;
    int image;

    public PlantModel(String commonName, String sciPlantName, String plantDesc, String care, String harvest, String pestDisease, String varieties, String water, String ytLink, int image) {
        this.commonName = commonName;
        this.sciName = sciPlantName;
        this.description = plantDesc;
        this.care = care;
        this.harvest = harvest;
        this.pestsDiseases = pestDisease;
        this.varieties = varieties;
        this.water = water;
        this.ytLink = ytLink;
        this.image = image;
    }

    public PlantModel(){

    }

    public PlantModel(String commonName, String sciName, int image) {
        this.commonName = commonName;
        this.sciName = sciName;
        this.image = image;
    }

    public String getCommonPlantName() {
        return commonName;
    }

    public String getSciName() {
        return sciName;
    }

    public String getPlantDesc() {
        return description;
    }

    public String getCare() {
        return care;
    }

    public String getHarvest() {
        return harvest;
    }

    public String getPestsDiseases() {
        return pestsDiseases;
    }

    public String getVarieties() {
        return varieties;
    }

    public String getWater() {
        return water;
    }

    public String getYtLink() {
        return ytLink;
    }

    public int getImage() {
        return image;
    }
}
