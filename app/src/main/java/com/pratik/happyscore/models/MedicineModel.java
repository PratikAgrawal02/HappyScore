package com.pratik.happyscore.models;

public class MedicineModel {

    String date;
    String time;
    String title;
    String discription;
    Boolean status;

    public MedicineModel(String date, String time, String title, String discription, Boolean status) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.discription = discription;
        this.status = status;
    }

    public MedicineModel() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
