// Fichier : src/main/java/com/gestionevenements/model/Organisateur.java
package com.gestionevenements.model;

import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant {
    private List<Evenement> evenementsOrganises = new ArrayList<>();
    public Organisateur() {}
    public Organisateur(String id, String nom, String email) { super(id, nom, email); }
    public List<Evenement> getEvenementsOrganises() { return evenementsOrganises; }
}