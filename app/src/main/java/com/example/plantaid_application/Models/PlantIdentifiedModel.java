package com.example.plantaid_application.Models;

public class PlantIdentifiedModel {

    public String idImage, commonName, sciName, family, date;

    public PlantIdentifiedModel(){

    }

    public PlantIdentifiedModel(String idImage, String commonName, String sciName, String family, String date) {
        this.idImage = idImage;
        this.commonName = commonName;
        this.sciName = sciName;
        this.family = family;
        this.date = date;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
