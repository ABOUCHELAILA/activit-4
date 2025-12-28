
---

## 3. Micro-services fonctionnels

### 3.1 Keynote-Service

- **Responsabilité** : Gestion des keynotes (CRUD).
- **Technologies** :
  - Spring Boot (REST Controller, Spring Data JPA).
  - Base de données relationnelle (ex : MySQL/PostgreSQL).
- **API principales (exemples)** :
  - `GET /api/keynotes`
  - `GET /api/keynotes/{id}`
  - `POST /api/keynotes`
  - `PUT /api/keynotes/{id}`
  - `DELETE /api/keynotes/{id}`
- **Documentation** : OpenAPI/Swagger (springdoc-openapi).

### 3.2 Conference-Service

- **Responsabilité** :
  - Gestion des conférences (CRUD).
  - Gestion des **reviews** associées à une conférence.
- **Technologies** :
  - Spring Boot (REST).
  - Spring Data JPA.
  - Base de données relationnelle (ex : MySQL/PostgreSQL).
- **API principales (exemples)** :
  - Conférences :
    - `GET /api/conferences`
    - `GET /api/conferences/{id}`
    - `POST /api/conferences`
    - `PUT /api/conferences/{id}`
    - `DELETE /api/conferences/{id}`
  - Reviews :
    - `GET /api/conferences/{id}/reviews`
    - `POST /api/conferences/{id}/reviews`
    - `GET /api/reviews/{id}`
- **Intégration Keynote** :
  - Pour afficher les détails d’un keynote lié à une conférence, le Conference-Service communique avec le Keynote-Service soit via REST direct (Feign Client), soit via API Gateway.

---

## 4. Micro-services techniques

### 4.1 API Gateway – Spring Cloud Gateway

- **Rôle** :
  - Point d’entrée unique pour le frontend Angular.
  - Routing vers les micro-services :
    - `/keynotes/**` → Keynote-Service
    - `/conferences/**` → Conference-Service
  - Intégration avec Keycloak :
    - Validation des tokens JWT (Oauth2 Resource Server).
    - Filtrage des requêtes en fonction du rôle (ROLE_ADMIN, ROLE_USER…).
- **Fonctionnalités possibles** :
  - Rate limiting.
  - Logs et traçabilité.
  - Ajout d’entêtes (correlation-id, etc.).

### 4.2 Service Discovery – Eureka Server ou Consul

- **Rôle** :
  - Enregistrer dynamiquement les instances des micro-services.
  - Permettre au Gateway et aux autres services de découvrir les services par leur **nom logique**.
- **Fonctionnement** :
  - Chaque micro-service (Keynote, Conference, Gateway) s’enregistre au démarrage.
  - Le Gateway fait du load-balancing vers plusieurs instances si nécessaire.

### 4.3 Config Server – Spring Cloud Config ou Consul Config

- **Rôle** :
  - Centraliser la configuration des micro-services (ports, URLs, paramètres DB, Keycloak, etc.).
  - Gérer plusieurs profils : `dev`, `test`, `prod`.
- **Stockage de la config** :
  - Repository Git ou KV Store (selon Spring Cloud Config ou Consul Config).
- **Avantages** :
  - Changement de configuration sans redéploiement complet (avec `@RefreshScope`).
  - Configuration homogène et versionnée.

---

## 5. Frontend Web – Angular

- **Rôle** :
  - Interface utilisateur pour :
    - Gérer les keynotes.
    - Gérer les conférences et leurs reviews.
    - Visualiser les scores, le nombre d’inscrits, etc.
- **Architecture** :
  - SPA Angular (modules, composants, services).
  - Communication HTTP avec le Gateway (ex : `/api/keynotes`, `/api/conferences`).
- **Sécurité** :
  - Intégration OIDC :
    - Redirection vers Keycloak pour login.
    - Récupération du token d’accès (Access Token) et envoi dans l’entête `Authorization: Bearer <token>`.

---

## 6. Sécurité – Oauth2 / OIDC avec Keycloak

- **Provider** : Keycloak.
- **Rôles** (exemples) :
  - `ROLE_ADMIN` : création/suppression de conférences, keynotes.
  - `ROLE_USER` : consulter les conférences, ajouter des reviews.
- **Mécanisme** :
  1. L’utilisateur accède au frontend Angular.
  2. Angular redirige vers Keycloak pour l’authentification (OIDC).
  3. Après succès, Keycloak renvoie un token (JWT).
  4. Angular envoie ce token au Gateway pour chaque requête.
  5. Le Gateway (et/ou les micro-services) valident le token (signature, audience, rôles).

- **Configuration technique** :
  - Spring Security + Spring Boot Oauth2 Resource Server.
  - Configuration des `client-id`, `realm`, `issuer-uri`, etc. dans le Config Server.

---

## 7. Documentation – OpenAPI / Swagger

- **Objectif** :
  - Générer automatiquement la documentation des API REST.
  - Faciliter les tests (Swagger UI).
- **Outils** :
  - `springdoc-openapi` (recommandé).
- **Résultat** :
  - Pour chaque service :
    - `http://<host>:<port>/v3/api-docs`
    - `http://<host>:<port>/swagger-ui.html`

---

## 8. Résilience et Tolérance aux pannes – Resilience4J

- **Objectif** :
  - Eviter les effets de cascade en cas d’indisponibilité d’un micro-service.
  - Améliorer la robustesse globale.
- **Patterns utilisés** :
  - **Circuit Breaker** :
    - Exemple : lors des appels du Conference-Service vers Keynote-Service.
    - Si le service distant tombe, le circuit s’ouvre et renvoie une réponse de fallback.
  - **Retry** :
    - Retenter une requête un certain nombre de fois avant d’échouer.
  - **Rate Limiter / Bulkhead** (optionnel).
- **Intégration** :
  - `resilience4j-spring-boot2`.
  - Configuration dans `application.yml` ou via Config Server.

---

## 9. Communication et protocoles

- **Entre frontend et backend** :
  - HTTP/HTTPS + JSON.
  - OIDC/Oauth2 pour la sécurité.
- **Entre micro-services** :
  - REST (HTTP) + JSON.
  - Découverte via Eureka/Consul.
  - Load-balancing via Gateway ou Spring Cloud LoadBalancer.
- **Entre Config Server et Git / Consul** :
  - HTTP/HTTPS ou protocole spécifique à l’outil choisi.

---

## 10. Environnement de développement

- **Backend** :
  - Java (Spring Boot).
  - Maven ou Gradle.
- **Frontend** :
  - Angular (TypeScript, Node.js, npm).
- **Infra** :
  - Docker (optionnel) pour lancer Keycloak, Eureka/Consul, BDD, etc.
  - Base de données : MySQL / PostgreSQL.

---

## 11. Résumé

L’architecture proposée est une architecture **micro-services** moderne, supportant :

- La séparation claire des responsabilités (Keynote-Service, Conference-Service).
- La scalabilité (service discovery, gateway, load balancing).
- La sécurité (Keycloak, Oauth2, OIDC).
- La résilience (Resilience4J).
- La documentation (OpenAPI / Swagger).
- Une expérience utilisateur riche via un frontend **Angular**.
