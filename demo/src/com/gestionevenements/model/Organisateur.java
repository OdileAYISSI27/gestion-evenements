package com.gestionevenements.model;

import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant {
    private List<Evenement> evenementsOrganises; // [cite: 3]

    public Organisateur(String id, String nom, String email) {
        super(id, nom, email);
        this.evenementsOrganises = new ArrayList<>();
    }

    public void ajouterEvenementOrganise(Evenement evenement) {
        if (evenement != null && !this.evenementsOrganises.contains(evenement)) {
            this.evenementsOrganises.add(evenement);
        }
    }
}