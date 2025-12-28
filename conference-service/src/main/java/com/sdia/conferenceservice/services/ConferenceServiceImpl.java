package com.sdia.conferenceservice.services;

import com.sdia.conferenceservice.clients.KeynoteClient;
import com.sdia.conferenceservice.dtos.ConferenceRequestDTO;
import com.sdia.conferenceservice.dtos.ConferenceResponseDTO;
import com.sdia.conferenceservice.dtos.ReviewRequestDTO;
import com.sdia.conferenceservice.dtos.ReviewResponseDTO;
import com.sdia.conferenceservice.entities.Conference;
import com.sdia.conferenceservice.entities.Review;
import com.sdia.conferenceservice.mappers.ConferenceMapper;
import com.sdia.conferenceservice.model.Keynote;
import com.sdia.conferenceservice.repositories.ConferenceRepository;
import com.sdia.conferenceservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final ReviewRepository reviewRepository;
    private final ConferenceMapper conferenceMapper;
    private final KeynoteClient keynoteClient;

    @Override
    public ConferenceResponseDTO save(ConferenceRequestDTO requestDTO) {
        Conference conference = conferenceMapper.fromConferenceRequestDTO(requestDTO);
        Conference savedConference = conferenceRepository.save(conference);
        return conferenceMapper.fromConference(savedConference);
    }

    @Override
    public ConferenceResponseDTO update(Long id, ConferenceRequestDTO requestDTO) {
        Conference conference = conferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Conference not found"));
        if (requestDTO.getTitre() != null) conference.setTitre(requestDTO.getTitre());
        if (requestDTO.getDate() != null) conference.setDate(requestDTO.getDate());
        if (requestDTO.getDuree() != 0) conference.setDuree(requestDTO.getDuree());
        if (requestDTO.getKeynoteId() != null) conference.setKeynoteId(requestDTO.getKeynoteId());
        Conference updatedConference = conferenceRepository.save(conference);
        return conferenceMapper.fromConference(updatedConference);
    }

    @Override
    public void delete(Long id) {
        conferenceRepository.deleteById(id);
    }

    @Override
    public ConferenceResponseDTO findById(Long id) {
        Conference conference = conferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Conference not found"));
        ConferenceResponseDTO responseDTO = conferenceMapper.fromConference(conference);
        try {
            Keynote keynote = keynoteClient.getKeynoteById(conference.getKeynoteId());
            responseDTO.setKeynote(keynote);
        } catch (Exception e) {
            // Fallback if keynote service is down
        }
        return responseDTO;
    }

    @Override
    public List<ConferenceResponseDTO> findAll() {
        return conferenceRepository.findAll().stream()
                .map(conf -> {
                    ConferenceResponseDTO dto = conferenceMapper.fromConference(conf);
                    try {
                        dto.setKeynote(keynoteClient.getKeynoteById(conf.getKeynoteId()));
                    } catch (Exception e) {}
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO addReviewToConference(ReviewRequestDTO requestDTO) {
        Conference conference = conferenceRepository.findById(requestDTO.getConferenceId())
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        Review review = conferenceMapper.fromReviewRequestDTO(requestDTO);
        review.setConference(conference);
        Review savedReview = reviewRepository.save(review);
        return conferenceMapper.fromReview(savedReview);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByConferenceId(Long conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        return reviewRepository.findByConference(conference).stream()
                .map(conferenceMapper::fromReview)
                .collect(Collectors.toList());
    }
}
