// Fichier : src/main/java/com/gestionevenements/service/EmailNotificationService.java
package com.gestionevenements.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class EmailNotificationService implements NotificationService {
    @Override
    public void envoyerNotification(String message) {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2); // Simule un délai
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("✅ Email envoyé avec le message : '" + message + "'");
        });
    }
}
