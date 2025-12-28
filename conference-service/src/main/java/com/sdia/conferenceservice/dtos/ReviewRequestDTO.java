package com.sdia.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewRequestDTO {
    private String texte;
    private int note;
    private Long conferenceId;
}
