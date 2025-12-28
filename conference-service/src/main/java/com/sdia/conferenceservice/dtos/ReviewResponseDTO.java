package com.sdia.conferenceservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewResponseDTO {
    private Long id;
    private String texte;
    private int note;
}
