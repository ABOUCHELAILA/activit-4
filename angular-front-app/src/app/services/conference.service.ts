import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conference, Review } from '../models/conference.model';

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {
  private apiUrl = 'http://localhost:8888/conferences';

  constructor(private http: HttpClient) { }

  getAllConferences(): Observable<Conference[]> {
    return this.http.get<Conference[]>(this.apiUrl);
  }

  getConferenceById(id: number): Observable<Conference> {
    return this.http.get<Conference>(`${this.apiUrl}/${id}`);
  }

  createConference(conference: Conference): Observable<Conference> {
    return this.http.post<Conference>(this.apiUrl, conference);
  }

  updateConference(id: number, conference: Conference): Observable<Conference> {
    return this.http.put<Conference>(`${this.apiUrl}/${id}`, conference);
  }

  deleteConference(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  getReviewsByConferenceId(id: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/${id}/reviews`);
  }

  addReviewToConference(id: number, review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.apiUrl}/${id}/reviews`, review);
  }
}
