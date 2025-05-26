// Fichier : com/gestionevenements/model/Evenement.java

package com.gestionevenements.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import com.gestionevenements.observer.ObservateurEvenement; // Importer l'interface

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type" // Un champ 'type' sera
                                                                                                // ajouté au JSON pour
                                                                                                // identifier la classe
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Conference.class, name = "conference"),
        @JsonSubTypes.Type(value = Concert.class, name = "concert")
})
public abstract class Evenement {
    // ... les attributs id, nom, date, etc. ne changent pas ...
    protected String id;
    protected String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    protected List<Participant> participantsInscrits = new ArrayList<>();

    // NOUVEAU : La liste des observateurs
    private List<ObservateurEvenement> observateurs = new ArrayList<>();

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }

    // ... Getters ne changent pas ...
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public List<Participant> getParticipantsInscrits() {
        return participantsInscrits;
    }

    // NOUVELLES MÉTHODES POUR LE PATTERN OBSERVER
    public void ajouterObservateur(ObservateurEvenement observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(ObservateurEvenement observateur) {
        observateurs.remove(observateur);
    }

    public void notifierObservateurs(String message) {
        for (ObservateurEvenement observateur : observateurs) {
            observateur.mettreAJour(this, message);
        }
    }

    // ... les méthodes abstraites ne changent pas ...
    public abstract void ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException;

    public abstract void annuler();

    public abstract void afficherDetails();
}