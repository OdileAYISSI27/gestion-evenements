package com.gestionevenements.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gestionevenements.model.Evenement;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ServicePersistance {

    private final ObjectMapper objectMapper;

    public ServicePersistance() {
        this.objectMapper = new ObjectMapper();
        // Pour que le JSON soit joliment formaté (indenté)
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Pour que Jackson puisse gérer les dates de type LocalDateTime
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Sauvegarde la map d'événements dans un fichier JSON.
     * @param evenements La map des événements à sauvegarder.
     * @param nomFichier Le nom du fichier de destination (ex: "events.json").
     */
    public void sauvegarderEvenements(Map<String, Evenement> evenements, String nomFichier) throws IOException {
        objectMapper.writeValue(new File(nomFichier), evenements);
        System.out.println("Les événements ont été sauvegardés dans " + nomFichier);
    }

    /**
     * Charge une map d'événements depuis un fichier JSON.
     * @param nomFichier Le nom du fichier à charger.
     * @return La map des événements.
     */
    public Map<String, Evenement> chargerEvenements(String nomFichier) throws IOException {
        File fichier = new File(nomFichier);
        if (!fichier.exists()) {
            System.out.println("Le fichier de sauvegarde n'existe pas. Retour d'une liste vide.");
            return new java.util.HashMap<>();
        }
        // On utilise TypeReference pour aider Jackson à comprendre le type complexe Map<String, Evenement>
        Map<String, Evenement> evenementsCharges = objectMapper.readValue(fichier, new TypeReference<Map<String, Evenement>>() {});
        System.out.println(evenementsCharges.size() + " événements ont été chargés depuis " + nomFichier);
        return evenementsCharges;
    }
}
