package com.gestionevenements.exception;

// Exception requise par le cahier des charges [cite: 4]
public class EvenementDejaExistantException extends Exception {
    public EvenementDejaExistantException(String message) {
        super(message);
    }
}
