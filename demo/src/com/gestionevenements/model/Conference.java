package com.gestionevenements.model;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import java.time.LocalDateTime;
import java.util.List;

public class Conference extends Evenement {
    private String theme; // [cite: 1]
    private List<Intervenant> intervenants; // [cite: 2]

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme, List<Intervenant> intervenants) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = intervenants;
    }

    @Override
    public void ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException {
        if (getParticipantsInscrits().size() >= getCapaciteMax()) {
            throw new CapaciteMaxAtteinteException("Capacité maximale atteinte pour la conférence '" + getNom() + "'.");
        }
        if (!getParticipantsInscrits().contains(participant)) {
            getParticipantsInscrits().add(participant);
        }
    }

 
@Override
public void annuler() {
    String message = "La conference de '" + this.intervenants + "' prévu le " + getDate() + " est annulé.";
    System.out.println("ANNULATION SYSTÈME: " + message);

    // On notifie tous les observateurs abonnés !
    notifierObservateurs(message);
}

    @Override
    public void afficherDetails() {
        System.out.println("--- CONFÉRENCE ---");
        System.out.println("Titre: " + getNom() + " sur le thème '" + this.theme + "'");
        System.out.println("Date: " + getDate() + " | Lieu: " + getLieu());
    }
}