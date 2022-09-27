package com.example.plantaid_application;

public class PlantModel {
    String commonPlantName;
    String sciPlantName;
    String plantDesc;
    int image;

    public PlantModel(String commonPlantName, String sciPlantName, String plantDesc, int image) {
        this.commonPlantName = commonPlantName;
        this.sciPlantName = sciPlantName;
        this.plantDesc = plantDesc;
        this.image = image;
    }

    public String getCommonPlantName() {
        return commonPlantName;
    }

    public String getSciPlantName() {
        return sciPlantName;
    }

    public String getPlantDesc() {return plantDesc; }

    public int getImage() {
        return image;
    }
}