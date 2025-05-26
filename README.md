# gestion-evenements

## Fonctionnalités
- Création/annulation d'événements (conférence, concert)
- Gestion des inscriptions
- Notifications en temps réel (Observer + Strategy)
- Persistance JSON/XML
- Exécution asynchrone (CompletableFuture)
- Tests unitaires avec JUnit

## Technologies
- Java 11+
- Maven
- Jackson / JAXB
- JUnit 5

## Exécution
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.gestionevenements.Main"
