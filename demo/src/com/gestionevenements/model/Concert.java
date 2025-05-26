package com.gestionevenements.model;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import java.time.LocalDateTime;

public class Concert extends Evenement {
    private String artiste; // [cite: 2]
    private String genreMusical; // [cite: 2]

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    @Override
    public void ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException {
        if (getParticipantsInscrits().size() >= getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("Capacité maximale atteinte pour le concert '" + getNom() + "'.");
        }
        if (!getParticipantsInscrits().contains(participant)) {
            getParticipantsInscrits().add(participant);
        }
    }

   // Dans le fichier Concert.java

@Override
public void annuler() {
    String message = "Le concert de '" + this.artiste + "' prévu le " + getDate() + " est annulé.";
    System.out.println("ANNULATION SYSTÈME: " + message);

    // On notifie tous les observateurs abonnés !
    notifierObservateurs(message);
}

    @Override
    public void afficherDetails() {
        System.out.println("--- CONCERT ---");
        System.out.println("Artiste: " + this.artiste + " | Genre: " + this.genreMusical);
        System.out.println("Date: " + getDate() + " | Lieu: " + getLieu());
    }
}