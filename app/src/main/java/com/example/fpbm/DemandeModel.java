package com.example.fpbm;

public class DemandeModel {
    private String appapoinetement,title;

    public DemandeModel(String appapoinetement, String title){
        this.appapoinetement = appapoinetement;
        this.title = title;
    }

    public DemandeModel(){

    }

    public String getAppapoinetement() {
        return appapoinetement;
    }

    public void setAppapoinetement(String appapoinetement) {
        this.appapoinetement = appapoinetement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
