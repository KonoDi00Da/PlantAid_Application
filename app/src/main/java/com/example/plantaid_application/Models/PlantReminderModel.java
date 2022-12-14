package com.example.plantaid_application.Models;

public class PlantReminderModel {
    public String plantName, reminderType, date, time, userKey, reminderKey;

    public PlantReminderModel(){

    }

    public PlantReminderModel(String plantName, String reminderType, String date, String time, String userKey, String reminderKey) {
        this.plantName = plantName;
        this.reminderType = reminderType;
        this.date = date;
        this.time = time;
        this.userKey = userKey;
        this.reminderKey = reminderKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getReminderKey() {
        return reminderKey;
    }

    public void setReminderKey(String reminderKey) {
        this.reminderKey = reminderKey;
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
