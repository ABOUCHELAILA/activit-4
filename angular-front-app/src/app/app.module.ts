import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { KeynotesComponent } from './components/keynotes/keynotes.component';
import { ConferencesComponent } from './components/conferences/conferences.component';
import { ConferenceDetailsComponent } from './components/conference-details/conference-details.component';

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
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
