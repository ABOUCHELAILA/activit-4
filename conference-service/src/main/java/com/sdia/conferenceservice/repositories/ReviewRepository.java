package com.sdia.conferenceservice.repositories;

import com.sdia.conferenceservice.entities.Conference;
import com.sdia.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByConference(Conference conference);
}
