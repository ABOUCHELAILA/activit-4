package com.sdia.conferenceservice.services;

import com.sdia.conferenceservice.dtos.ConferenceRequestDTO;
import com.sdia.conferenceservice.dtos.ConferenceResponseDTO;
import com.sdia.conferenceservice.dtos.ReviewRequestDTO;
import com.sdia.conferenceservice.dtos.ReviewResponseDTO;

import java.util.List;

public interface ConferenceService {
    ConferenceResponseDTO save(ConferenceRequestDTO requestDTO);
    ConferenceResponseDTO update(Long id, ConferenceRequestDTO requestDTO);
    void delete(Long id);
    ConferenceResponseDTO findById(Long id);
    List<ConferenceResponseDTO> findAll();

    ReviewResponseDTO addReviewToConference(ReviewRequestDTO requestDTO);
    List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId);
}
