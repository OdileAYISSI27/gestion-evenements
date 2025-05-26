package com.gestionevenements.observer;

import com.gestionevenements.model.Evenement;

/**
 * L'interface pour tout objet souhaitant observer un événement.
 */
public interface ObservateurEvenement {
    /**
     * Méthode appelée par le sujet (Evenement) lorsqu'une notification est envoyée.
     * @param evenement L'événement qui a notifié.
     * @param message Le message de notification.
     */
    void mettreAJour(Evenement evenement, String message);
}