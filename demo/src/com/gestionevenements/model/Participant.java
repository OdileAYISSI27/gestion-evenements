package com.gestionevenements.model;

import com.gestionevenements.observer.ObservateurEvenement;
import com.gestionevenements.service.EmailNotificationService;
import com.gestionevenements.service.NotificationService;
import java.util.Objects;

public class Participant implements ObservateurEvenement {
    protected String id;
    protected String nom;
    protected String email;
    private final transient NotificationService notificationService;

    public Participant() {
        this.notificationService = new EmailNotificationService();
    }

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.notificationService = new EmailNotificationService();
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }

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

    @Override
    public void mettreAJour(Evenement evenement, String message) {
        String notificationContent = "Email vers " + this.email + ": " + message;
        notificationService.envoyerNotification(notificationContent);
    }
}