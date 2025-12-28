# Rapport d'Architecture Technique - Système de Gestion de Conférences

## 1. Introduction
Ce document présente l'architecture technique d'un système de gestion de conférences basé sur une architecture micro-services. Le système est conçu pour être scalable, résilient et sécurisé.

## 2. Architecture Globale
L'architecture repose sur plusieurs micro-services fonctionnels et techniques, communiquant de manière asynchrone ou via REST.

### 2.1 Micro-services Fonctionnels
- **Keynote-Service** : Gère les informations relatives aux conférenciers (Keynotes).
- **Conference-Service** : Gère les conférences et les évaluations (Reviews).

### 2.2 Micro-services Techniques (Infrastructure)
- **Service Discovery (Eureka)** : Permet l'enregistrement et la découverte dynamique des services.
- **API Gateway (Spring Cloud Gateway)** : Point d'entrée unique, gère le routage et la sécurité.
- **Config Server** : Centralise la configuration de tous les micro-services.

### 2.3 Frontend
- **Angular App** : Application web monopage (SPA) pour l'interaction utilisateur.

## 3. Détails du Keynote-Service
Le `keynote-service` est implémenté avec Spring Boot et expose une API REST pour les opérations CRUD sur les Keynotes.

### Technologies :
- Java 17, Spring Boot 3
- Spring Data JPA, H2 Database (ou MySQL en production)
- Lombok
- SpringDoc OpenAPI (Swagger)

### Endpoints :
- `GET /api/keynotes` : Lister tous les keynotes.
- `GET /api/keynotes/{id}` : Récupérer un keynote par ID.
- `POST /api/keynotes` : Ajouter un nouveau keynote.
- `PUT /api/keynotes/{id}` : Modifier un keynote.
- `DELETE /api/keynotes/{id}` : Supprimer un keynote.

## 4. Sécurité
La sécurité est assurée par **Keycloak** utilisant les protocoles OAuth2 et OpenID Connect (OIDC). L'API Gateway valide les tokens JWT avant de transmettre les requêtes aux services.

## 5. Résilience
L'utilisation de **Resilience4J** permet de mettre en place des patterns comme le *Circuit Breaker* et le *Retry* pour éviter les pannes en cascade.

## 6. Frontend Angular
L'application frontend est développée avec **Angular 17**. Elle communique avec les micro-services via l'**API Gateway**.

### Fonctionnalités :
- Affichage de la liste des keynotes.
- Affichage de la liste des conférences.
- Consultation des détails d'une conférence et de ses reviews.
- Ajout de reviews sur les conférences.

## 7. Guide de Test et Validation

Pour tester l'ensemble du système, suivez l'ordre de démarrage suivant :
1. `discovery-service` (Port 8761)
2. `config-service` (Port 8889)
3. `keynote-service` (Port 8081)
4. `conference-service` (Port 8082)
5. `gateway-service` (Port 8888)

### 7.1 Tests des Services Individuels (OpenAPI / Swagger)
Chaque micro-service dispose de sa propre interface Swagger pour tester les endpoints :
- **Keynote Service** : `http://localhost:8081/swagger-ui.html`
- **Conference Service** : `http://localhost:8082/swagger-ui.html`

### 7.2 Tests via l'API Gateway
La Gateway est le point d'entrée unique. Notez que la sécurité est activée (nécessite un token JWT de Keycloak).
- **Accès aux Keynotes** : `GET http://localhost:8888/keynotes`
- **Accès aux Conférences** : `GET http://localhost:8888/conferences`

### 7.3 Validation de l'Infrastructure
- **Eureka Dashboard** : `http://localhost:8761` pour vérifier que tous les services sont enregistrés.
- **Config Server** : `http://localhost:8889/keynote-service/default` pour vérifier que les configurations sont bien servies.

### 7.4 Exemples de commandes cURL (sans sécurité)
*Note : Si la sécurité est activée sur la Gateway, un header `Authorization: Bearer <TOKEN>` est requis.*

```bash
# Lister les keynotes via la gateway
curl http://localhost:8888/keynotes

# Créer un keynote
curl -X POST http://localhost:8081/api/keynotes -H "Content-Type: application/json" -d '{"nom":"Doe", "prenom":"John", "email":"john@doe.com", "fonction":"Speaker"}'

# Lister les conférences
curl http://localhost:8888/conferences
```

### 7.5 Test du Frontend Angular
1. Naviguer dans le dossier `angular-front-app`.
2. Installer les dépendances : `npm install`.
3. Lancer l'application : `ng serve`.
4. Accéder à `http://localhost:4200`.

L'application permet de visualiser les keynotes, les conférences et d'ajouter des reviews de manière interactive.

---
*Rapport généré le 28 Décembre 2025*
