package com.gestionevenements.model;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import java.time.LocalDateTime;

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    public Concert() { super(); }

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    public String getArtiste() { return artiste; }
    public String getGenreMusical() { return genreMusical; }

    @Override
    public void ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException {
        if (participantsInscrits.size() >= capaciteMax) {
            throw new CapaciteMaxAtteinteException("Capacité maximale atteinte pour le concert '" + getNom() + "'.");
        }
        if (!participantsInscrits.contains(participant)) {
            participantsInscrits.add(participant);
        }
    }

    @Override
    public void annuler() {
        String message = "Le concert de '" + this.artiste + "' prévu le " + getDate() + " est annulé.";
        System.out.println("ANNULATION SYSTÈME: " + message);
        notifierObservateurs(message);
    }

    @Override
    public void afficherDetails() {
        System.out.println("--- CONCERT ---");
        System.out.println("Artiste: " + this.artiste + " | Genre: " + this.genreMusical);
    }
}