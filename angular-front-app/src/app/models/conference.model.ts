export interface Conference {
  id?: number;
  titre: string;
  date: string;
  duree: number;
  keynoteId: number;
  keynote?: any;
}

export interface Review {
  id?: number;
  texte: string;
  note: number;
  conferenceId?: number;
}
