### Configuration de Keycloak

Pour que l'authentification fonctionne, vous devez configurer Keycloak comme suit :

1. Accédez à l'administration Keycloak : `http://localhost:8080` (admin/admin).
2. Créez un nouveau **Realm** nommé `sdia-realm`.
3. Créez un client pour le frontend :
   - **Client ID** : `angular-front`
   - **Client Protocol** : `openid-connect`
   - **Root URL** : `http://localhost:4200`
   - **Valid Redirect URIs** : `http://localhost:4200/*`
   - **Web Origins** : `*`
   - **Access Type** : `public` (ou Standard Flow enabled)
4. Créez des rôles : `ADMIN` et `USER`.
5. Créez des utilisateurs et attribuez-leur des rôles.

L'application Gateway est déjà configurée pour valider les tokens émis par ce realm.
