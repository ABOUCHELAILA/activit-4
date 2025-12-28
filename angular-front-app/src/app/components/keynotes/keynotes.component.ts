import { Component, OnInit } from '@angular/core';
import { KeynoteService } from '../../services/keynote.service';
import { Keynote } from '../../models/keynote.model';

@Component({
  selector: 'app-keynotes',
  templateUrl: './keynotes.component.html',
  styleUrls: ['./keynotes.component.css']
})
export class KeynotesComponent implements OnInit {
  keynotes: Keynote[] = [];

  constructor(private keynoteService: KeynoteService) { }

  ngOnInit(): void {
    this.loadKeynotes();
  }

  loadKeynotes(): void {
    this.keynoteService.getAllKeynotes().subscribe(data => {
      this.keynotes = data;
    });
  }

  deleteKeynote(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce keynote ?')) {
      this.keynoteService.deleteKeynote(id).subscribe(() => {
        this.loadKeynotes();
      });
    }
  }
}
