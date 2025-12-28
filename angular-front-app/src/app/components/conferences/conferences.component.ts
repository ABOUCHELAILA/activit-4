import { Component, OnInit } from '@angular/core';
import { ConferenceService } from '../../services/conference.service';
import { Conference } from '../../models/conference.model';

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.css']
})
export class ConferencesComponent implements OnInit {
  conferences: Conference[] = [];

  constructor(private conferenceService: ConferenceService) { }

  ngOnInit(): void {
    this.loadConferences();
  }

  loadConferences(): void {
    this.conferenceService.getAllConferences().subscribe(data => {
      this.conferences = data;
    });
  }

  deleteConference(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette conférence ?')) {
      this.conferenceService.deleteConference(id).subscribe(() => {
        this.loadConferences();
      });
    }
  }
}
