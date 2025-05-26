package com.gestionevenements.model;

public class Intervenant {
    private String nom;
    private String specialite;

    public Intervenant(String nom, String specialite) {
        this.nom = nom;
        this.specialite = specialite;
    }

    public String getNom() { return nom; }
    public String getSpecialite() { return specialite; }
}