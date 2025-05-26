// Fichier : src/main/java/com/gestionevenements/service/ServicePersistance.java
package com.gestionevenements.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gestionevenements.model.Evenement;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicePersistance {
    private final ObjectMapper objectMapper;

    public ServicePersistance() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void sauvegarderEvenements(Map<String, Evenement> evenements, String nomFichier) throws IOException {
        objectMapper.writeValue(new File(nomFichier), evenements);
    }

    public Map<String, Evenement> chargerEvenements(String nomFichier) throws IOException {
        File fichier = new File(nomFichier);
        if (!fichier.exists()) {
            return new HashMap<>();
        }
        return objectMapper.readValue(fichier, new TypeReference<Map<String, Evenement>>() {});
    }
}