package com.sdia.conferenceservice.web;

import com.sdia.conferenceservice.dtos.ConferenceRequestDTO;
import com.sdia.conferenceservice.dtos.ConferenceResponseDTO;
import com.sdia.conferenceservice.dtos.ReviewRequestDTO;
import com.sdia.conferenceservice.dtos.ReviewResponseDTO;
import com.sdia.conferenceservice.services.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConferenceController {
    private final ConferenceService conferenceService;

    @GetMapping("/conferences")
    public List<ConferenceResponseDTO> getAllConferences() {
        return conferenceService.findAll();
    }

    @GetMapping("/conferences/{id}")
    public ConferenceResponseDTO getConferenceById(@PathVariable Long id) {
        return conferenceService.findById(id);
    }

    @PostMapping("/conferences")
    public ConferenceResponseDTO saveConference(@RequestBody ConferenceRequestDTO requestDTO) {
        return conferenceService.save(requestDTO);
    }

    @PutMapping("/conferences/{id}")
    public ConferenceResponseDTO updateConference(@PathVariable Long id, @RequestBody ConferenceRequestDTO requestDTO) {
        return conferenceService.update(id, requestDTO);
    }

    @DeleteMapping("/conferences/{id}")
    public void deleteConference(@PathVariable Long id) {
        conferenceService.delete(id);
    }

    @GetMapping("/conferences/{id}/reviews")
    public List<ReviewResponseDTO> getReviewsByConference(@PathVariable Long id) {
        return conferenceService.getReviewsByConferenceId(id);
    }

    @PostMapping("/conferences/{id}/reviews")
    public ReviewResponseDTO addReviewToConference(@PathVariable Long id, @RequestBody ReviewRequestDTO requestDTO) {
        requestDTO.setConferenceId(id);
        return conferenceService.addReviewToConference(requestDTO);
    }
}
