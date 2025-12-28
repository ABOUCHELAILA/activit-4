package com.sdia.conferenceservice.mappers;

import com.sdia.conferenceservice.dtos.ConferenceRequestDTO;
import com.sdia.conferenceservice.dtos.ConferenceResponseDTO;
import com.sdia.conferenceservice.dtos.ReviewRequestDTO;
import com.sdia.conferenceservice.dtos.ReviewResponseDTO;
import com.sdia.conferenceservice.entities.Conference;
import com.sdia.conferenceservice.entities.Review;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ConferenceMapper {

    public Conference fromConferenceRequestDTO(ConferenceRequestDTO requestDTO) {
        Conference conference = new Conference();
        BeanUtils.copyProperties(requestDTO, conference);
        return conference;
    }

    public ConferenceResponseDTO fromConference(Conference conference) {
        ConferenceResponseDTO responseDTO = new ConferenceResponseDTO();
        BeanUtils.copyProperties(conference, responseDTO);
        return responseDTO;
    }

    public Review fromReviewRequestDTO(ReviewRequestDTO requestDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(requestDTO, review);
        return review;
    }

    public ReviewResponseDTO fromReview(Review review) {
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        BeanUtils.copyProperties(review, responseDTO);
        return responseDTO;
    }
}
