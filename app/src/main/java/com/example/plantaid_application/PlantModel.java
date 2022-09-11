package com.example.plantaid_application;

public class PlantModel {
    String commonPlantName;
    String sciPlantName;
    int image;

    public PlantModel(String commonPlantName, String sciPlantName, int image) {
        this.commonPlantName = commonPlantName;
        this.sciPlantName = sciPlantName;
        this.image = image;
    }

    public String getCommonPlantName() {
        return commonPlantName;
    }

    public String getSciPlantName() {
        return sciPlantName;
    }

    public int getImage() {
        return image;
    }
}
