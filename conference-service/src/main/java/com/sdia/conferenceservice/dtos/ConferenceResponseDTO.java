package com.sdia.conferenceservice.dtos;

import com.sdia.conferenceservice.model.Keynote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConferenceResponseDTO {
    private Long id;
    private String titre;
    private Date date;
    private double duree;
    private Long keynoteId;
    private Keynote keynote;
}
