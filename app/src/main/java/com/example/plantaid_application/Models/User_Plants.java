package com.example.plantaid_application.Models;

public class User_Plants {
    public String c_plantName;
    public String s_plantName;
    public String plantAge;

    public User_Plants() {

    }

    public User_Plants(String c_plantName, String s_plantName, String plantAge) {
        this.c_plantName = c_plantName;
        this.s_plantName = s_plantName;
        this.plantAge = plantAge;
    }

    public String getC_plantName() {
        return c_plantName;
    }

    public void setC_plantName(String c_plantName) {
        this.c_plantName = c_plantName;
    }

    public String getS_plantName() {
        return s_plantName;
    }

    public void setS_plantName(String s_plantName) {
        this.s_plantName = s_plantName;
    }

    public String getPlantAge() {
        return plantAge;
    }

    public void setPlantAge(String plantAge) {
        this.plantAge = plantAge;
    }
}
