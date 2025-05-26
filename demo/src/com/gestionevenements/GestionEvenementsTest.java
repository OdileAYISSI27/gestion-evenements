package com.gestionevenements;

import com.gestionevenements.exception.CapaciteMaxAtteinteException;
import com.gestionevenements.exception.EvenementDejaExistantException;
import com.gestionevenements.model.Concert;
import com.gestionevenements.model.Evenement;
import com.gestionevenements.model.Participant;
import com.gestionevenements.service.GestionEvenements;
import com.gestionevenements.service.ServicePersistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GestionEvenementsTest {

    private GestionEvenements gestionEvenements;
    private Participant participant;
    private Concert concert;

    @BeforeEach
    void setUp() {
        // Assure un état propre avant chaque test
        gestionEvenements = GestionEvenements.getInstance();
        gestionEvenements.clear();
        participant = new Participant("P01", "Marie", "marie@test.com");
        concert = new Concert("C01", "Test Concert", LocalDateTime.now(), "Test Lieu", 1, "Test Artist", "Test Genre");
    }

    @Test
    @DisplayName("Doit permettre l'inscription d'un participant à un événement")
    void testInscriptionParticipant() throws CapaciteMaxAtteinteException {
        concert.ajouterParticipant(participant);
        assertTrue(concert.getParticipantsInscrits().contains(participant));
        assertEquals(1, concert.getParticipantsInscrits().size());
    }

    @Test
    @DisplayName("Doit lever CapaciteMaxAtteinteException si l'événement est plein")
    void testLeverExceptionCapaciteMax() throws CapaciteMaxAtteinteException {
        Participant participant2 = new Participant("P02", "Paul", "paul@test.com");
        concert.ajouterParticipant(participant); // La capacité est de 1, donc c'est plein

        // Vérifie que l'ajout d'un deuxième participant lève bien l'exception
        assertThrows(CapaciteMaxAtteinteException.class, () -> {
            concert.ajouterParticipant(participant2);
        });
    }

    @Test
    @DisplayName("Doit lever EvenementDejaExistantException lors de l'ajout d'un duplicata")
    void testLeverExceptionEvenementExistant() throws EvenementDejaExistantException {
        gestionEvenements.ajouterEvenement(concert);

        // Vérifie que l'ajout du même événement une seconde fois lève l'exception
        assertThrows(EvenementDejaExistantException.class, () -> {
            gestionEvenements.ajouterEvenement(concert);
        });
    }

    @Test
    @DisplayName("Doit sauvegarder et recharger les événements correctement")
    void testSerializationDeserialisation(@TempDir File tempDir) throws EvenementDejaExistantException, IOException {
        ServicePersistance servicePersistance = new ServicePersistance();
        File fichierDeTest = new File(tempDir, "test-events.json");

        gestionEvenements.ajouterEvenement(concert);

        // 1. Sauvegarder
        servicePersistance.sauvegarderEvenements(gestionEvenements.getTousLesEvenements(),
                fichierDeTest.getAbsolutePath());
        assertTrue(fichierDeTest.exists());

        // 2. Vider le gestionnaire en mémoire
        gestionEvenements.clear();
        assertTrue(gestionEvenements.getTousLesEvenements().isEmpty());

        // 3. Recharger
        Map<String, Evenement> evenementsCharges = servicePersistance
                .chargerEvenements(fichierDeTest.getAbsolutePath());
        assertFalse(evenementsCharges.isEmpty());
        assertEquals(1, evenementsCharges.size());

        // 4. Vérifier les données
        Evenement evenementRecharge = evenementsCharges.get("C01");
        assertNotNull(evenementRecharge);
        assertEquals("Test Concert", evenementRecharge.getNom());
        assertTrue(evenementRecharge instanceof Concert);
        assertEquals("Test Artist", ((Concert) evenementRecharge).getArtiste());
    }
}