// Fichier : src/main/java/com/gestionevenements/service/GestionEvenements.java
package com.gestionevenements.service;

import com.gestionevenements.exception.EvenementDejaExistantException;
import com.gestionevenements.model.Evenement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestionEvenements {
    private static final GestionEvenements instance = new GestionEvenements();
    private Map<String, Evenement> evenements;

    private GestionEvenements() {
        evenements = new HashMap<>();
    }

    public static GestionEvenements getInstance() {
        return instance;
    }

    public void ajouterEvenement(Evenement evenement) throws EvenementDejaExistantException {
        if (evenements.containsKey(evenement.getId())) {
            throw new EvenementDejaExistantException("Un événement avec l'ID '" + evenement.getId() + "' existe déjà.");
        }
        evenements.put(evenement.getId(), evenement);
    }
    
    public Evenement rechercherEvenement(String id) {
        return evenements.get(id);
    }
    
    public Map<String, Evenement> getTousLesEvenements() {
        return Collections.unmodifiableMap(evenements);
    }

    public List<Evenement> rechercherEvenementsParLieu(String lieu) {
        return evenements.values().stream()
            .filter(evenement -> evenement.getLieu().equalsIgnoreCase(lieu))
            .collect(Collectors.toList());
    }

    public void clear() {
        evenements.clear();
    }
}