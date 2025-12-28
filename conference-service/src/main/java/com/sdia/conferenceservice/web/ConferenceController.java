package com.sdia.conferenceservice.web;

import com.sdia.conferenceservice.clients.KeynoteClient;
import com.sdia.conferenceservice.entities.Conference;
import com.sdia.conferenceservice.entities.Review;
import com.sdia.conferenceservice.model.Keynote;
import com.sdia.conferenceservice.repositories.ConferenceRepository;
import com.sdia.conferenceservice.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ConferenceController {
    private ConferenceRepository conferenceRepository;
    private ReviewRepository reviewRepository;
    private KeynoteClient keynoteClient;

    @GetMapping("/conferences")
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @GetMapping("/conferences/{id}")
    public Conference getConferenceById(@PathVariable Long id) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if (conference != null && conference.getKeynoteId() != null) {
            // Ici on pourrait enrichir l'objet si on avait un DTO, 
            // mais pour l'instant on se contente de l'id
        }
        return conference;
    }

    @PostMapping("/conferences")
    public Conference saveConference(@RequestBody Conference conference) {
        return conferenceRepository.save(conference);
    }

    @PutMapping("/conferences/{id}")
    public Conference updateConference(@PathVariable Long id, @RequestBody Conference conference) {
        conference.setId(id);
        return conferenceRepository.save(conference);
    }

    @DeleteMapping("/conferences/{id}")
    public void deleteConference(@PathVariable Long id) {
        conferenceRepository.deleteById(id);
    }

    @GetMapping("/conferences/{id}/reviews")
    public List<Review> getReviewsByConference(@PathVariable Long id) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        return conference != null ? conference.getReviews() : null;
    }

    @PostMapping("/conferences/{id}/reviews")
    public Review addReviewToConference(@PathVariable Long id, @RequestBody Review review) {
        Conference conference = conferenceRepository.findById(id).orElse(null);
        if (conference != null) {
            review.setConference(conference);
            return reviewRepository.save(review);
        }
        return null;
    }
}
