import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';

import { AppComponent } from './app.component';
import { KeynotesComponent } from './components/keynotes/keynotes.component';
import { ConferencesComponent } from './components/conferences/conferences.component';
import { ConferenceDetailsComponent } from './components/conference-details/conference-details.component';

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'sdia-realm',
        clientId: 'angular-front'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

const routes: Routes = [
  { path: 'keynotes', component: KeynotesComponent },
  { path: 'conferences', component: ConferencesComponent },
  { path: 'conferences/:id', component: ConferenceDetailsComponent },
  { path: '', redirectTo: '/conferences', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    KeynotesComponent,
    ConferencesComponent,
    ConferenceDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    KeycloakAngularModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
