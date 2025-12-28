package com.sdia.keynoteservice;

import com.sdia.keynoteservice.dtos.KeynoteRequestDTO;
import com.sdia.keynoteservice.services.KeynoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeynoteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(KeynoteService keynoteService) {
        return args -> {
            keynoteService.save(KeynoteRequestDTO.builder()
                    .nom("Einstein")
                    .prenom("Albert")
                    .email("albert@relativity.com")
                    .fonction("Physicist")
                    .build());
            keynoteService.save(KeynoteRequestDTO.builder()
                    .nom("Tesla")
                    .prenom("Nikola")
                    .email("nikola@electricity.com")
                    .fonction("Inventor")
                    .build());
        };
    }
}
