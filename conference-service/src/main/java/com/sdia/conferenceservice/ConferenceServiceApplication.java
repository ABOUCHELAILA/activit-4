package com.sdia.conferenceservice;

import com.sdia.conferenceservice.entities.Conference;
import com.sdia.conferenceservice.entities.Review;
import com.sdia.conferenceservice.repositories.ConferenceRepository;
import com.sdia.conferenceservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ConferenceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ConferenceRepository conferenceRepository, ReviewRepository reviewRepository) {
        return args -> {
            conferenceRepository.save(Conference.builder()
                    .titre("Spring Cloud Conference")
                    .date(new Date())
                    .duree(60)
                    .keynoteId(1L)
                    .build());
            conferenceRepository.save(Conference.builder()
                    .titre("Microservices Architecture")
                    .date(new Date())
                    .duree(45)
                    .keynoteId(2L)
                    .build());

            conferenceRepository.findAll().forEach(conf -> {
                reviewRepository.save(Review.builder()
                        .texte("Excellent!")
                        .note(5)
                        .conference(conf)
                        .build());
                reviewRepository.save(Review.builder()
                        .texte("Very good")
                        .note(4)
                        .conference(conf)
                        .build());
            });
        };
    }
}
