// Fichier : com/gestionevenements/model/Participant.java

package com.gestionevenements.model;

import com.gestionevenements.observer.ObservateurEvenement; // Importer l'interface
import java.util.Objects;

// Le participant est maintenant un observateur
public class Participant implements ObservateurEvenement {
    protected String id;
    protected String nom;
    protected String email;

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    // Getters... (aucun changement ici)
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }

    // equals et hashCode... (aucun changement ici)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // NOUVELLE MÉTHODE DE L'INTERFACE
    @Override
    public void mettreAJour(Evenement evenement, String message) {
        System.out.println("------------------------------------------");
        System.out.println("NOTIFICATION POUR: " + this.nom);
        System.out.println("Événement: " + evenement.getNom());
        System.out.println("Message: " + message);
        System.out.println("------------------------------------------");
    }
}