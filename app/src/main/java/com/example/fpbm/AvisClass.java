package com.example.fpbm;

import java.util.ArrayList;

public class AvisClass {
    private String affiche;
    private String date;
    private String description;
    private String title;
    boolean expanded;

    public AvisClass() {
    }

    public AvisClass(String affiche, String date, String description, String title) {
        this.affiche = affiche;
        this.date = date;
        this.description = description;
        this.title = title;
        this.expanded = false;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


}
