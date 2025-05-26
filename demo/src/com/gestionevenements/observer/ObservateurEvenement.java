// Fichier : src/main/java/com/gestionevenements/observer/ObservateurEvenement.java
package com.gestionevenements.observer;

import com.gestionevenements.model.Evenement;

public interface ObservateurEvenement {
    void mettreAJour(Evenement evenement, String message);
}