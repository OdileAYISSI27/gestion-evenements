package com.gestionevenements;

import com.gestionevenements.exception.EvenementDejaExistantException;
import com.gestionevenements.model.*;
import com.gestionevenements.service.GestionEvenements;
import com.gestionevenements.service.ServicePersistance;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GestionEvenements ge = GestionEvenements.getInstance();
        ServicePersistance sp = new ServicePersistance();
        String nomFichier = "events.json";

        // --- PARTIE 1: CRÉATION ET SAUVEGARDE ---
        System.out.println("--- CRÉATION ET SAUVEGARDE DES ÉVÉNEMENTS ---");
        try {
            Concert concert = new Concert("C01", "Concert de l'Unité", LocalDateTime.now().plusMonths(3), "Yaoundé", 1000, "Shan'L", "Afropop");
            Conference conf = new Conference("CF01", "Tech for Future", LocalDateTime.now().plusMonths(2), "Douala", 250, "IA et Avenir", Collections.emptyList());

            ge.ajouterEvenement(concert);
            ge.ajouterEvenement(conf);

            sp.sauvegarderEvenements(ge.getTousLesEvenements(), nomFichier);

        } catch (EvenementDejaExistantException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- CHARGEMENT DES ÉVÉNEMENTS ---");
        // --- PARTIE 2: CHARGEMENT ---
        // Pour bien tester, on pourrait vider le gestionnaire avant de charger
        // ge.getTousLesEvenements().clear(); // Note: notre map est non-modifiable, donc on ne peut pas faire ça directement.
        // On va donc simuler un redémarrage en chargeant dans une nouvelle map.
        try {
            Map<String, Evenement> evenementsCharges = sp.chargerEvenements(nomFichier);
            System.out.println("Événements chargés avec succès :");
            for (Evenement evt : evenementsCharges.values()) {
                evt.afficherDetails();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des événements: " + e.getMessage());
        }
    }
}