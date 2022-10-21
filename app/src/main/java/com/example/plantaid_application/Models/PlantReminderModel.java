package com.example.plantaid_application.Models;

public class PlantReminderModel {
    public String plantName, reminderType, date, time;

    public PlantReminderModel(){

    }

    public PlantReminderModel(String plantName, String reminderType, String date, String time) {
        this.plantName = plantName;
        this.reminderType = reminderType;
        this.date = date;
        this.time = time;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
