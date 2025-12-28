package com.sdia.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConferenceRequestDTO {
    private String titre;
    private Date date;
    private double duree;
    private Long keynoteId;
}
