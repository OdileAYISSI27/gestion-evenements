package com.gestionevenements.model;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Conference extends Evenement {
    private String theme;
    private List<Intervenant> intervenants;

    public Conference() { super(); }

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme, List<Intervenant> intervenants) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = intervenants;
    }

    public String getTheme() { return theme; }
    public List<Intervenant> getIntervenants() { return intervenants; }

    @Override
    public void ajouterParticipant(Participant participant) throws CapaciteMaxAtteinteException {
        if (participantsInscrits.size() >= capaciteMax) {
            throw new CapaciteMaxAtteinteException("Capacité maximale atteinte pour la conférence '" + getNom() + "'.");
        }
        if (!participantsInscrits.contains(participant)) {
            participantsInscrits.add(participant);
        }
    }

    @Override
    public void annuler() {
        String message = "La conférence '" + getNom() + "' est annulée.";
        System.out.println("ANNULATION SYSTÈME: " + message);
        notifierObservateurs(message);
    }

    @Override
    public void afficherDetails() {
        System.out.println("--- CONFÉRENCE ---");
        System.out.println("Titre: " + getNom() + " sur le thème '" + this.theme + "'");
        String nomsIntervenants = "N/A";
        if (intervenants != null && !intervenants.isEmpty()) {
            nomsIntervenants = intervenants.stream().map(Intervenant::getNom).collect(Collectors.joining(", "));
        }
        System.out.println("Intervenants: " + nomsIntervenants);
    }
}