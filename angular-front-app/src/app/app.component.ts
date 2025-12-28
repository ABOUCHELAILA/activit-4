import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular-front-app';
  public profile: KeycloakProfile | null = null;

  constructor(public keycloakService: KeycloakService) {}

  public async ngOnInit() {
    if (await this.keycloakService.isLoggedIn()) {
      this.profile = await this.keycloakService.loadUserProfile();
    }
  }

  public onLogin() {
    this.keycloakService.login();
  }

  public onLogout() {
    this.keycloakService.logout();
  }
}
