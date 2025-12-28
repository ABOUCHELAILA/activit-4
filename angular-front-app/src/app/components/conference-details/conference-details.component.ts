import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConferenceService } from '../../services/conference.service';
import { Conference, Review } from '../../models/conference.model';

@Component({
  selector: 'app-conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css']
})
export class ConferenceDetailsComponent implements OnInit {
  conference?: Conference;
  reviews: Review[] = [];
  newReview: Review = { texte: '', note: 5 };

  constructor(
    private route: ActivatedRoute,
    private conferenceService: ConferenceService
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadConference(id);
    this.loadReviews(id);
  }

  loadConference(id: number): void {
    this.conferenceService.getConferenceById(id).subscribe(data => {
      this.conference = data;
    });
  }

  loadReviews(id: number): void {
    this.conferenceService.getReviewsByConferenceId(id).subscribe(data => {
      this.reviews = data;
    });
  }

  addReview(): void {
    if (this.conference?.id) {
      this.conferenceService.addReviewToConference(this.conference.id, this.newReview).subscribe(() => {
        this.loadReviews(this.conference!.id!);
        this.newReview = { texte: '', note: 5 };
      });
    }
  }
}
