package com.example.fpbm;

public class NotesModel {
    private String devMobile;
    private String python;
    private String gestionProjet;
    private String semester;

    public NotesModel() {

    }

    public NotesModel(String devMobile, String python, String gestionProjet, String semester) {
        this.devMobile = devMobile;
        this.python = python;
        this.gestionProjet = gestionProjet;
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDevMobile() {
        return devMobile;
    }

    public void setDevMobile(String devMobile) {
        this.devMobile = devMobile;
    }

    public String getPython() {
        return python;
    }

    public void setPython(String python) {
        this.python = python;
    }

    public String getGestionProjet() {
        return gestionProjet;
    }

    public void setGestionProjet(String gestionProjet) {
        this.gestionProjet = gestionProjet;
    }
}
