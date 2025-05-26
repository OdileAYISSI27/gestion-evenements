package com.gestionevenements.exception;

// Exception requise par le cahier des charges [cite: 4]
public class CapaciteMaxAtteinteException extends Exception {
    public CapaciteMaxAtteinteException(String message) {
        super(message);
    }
}