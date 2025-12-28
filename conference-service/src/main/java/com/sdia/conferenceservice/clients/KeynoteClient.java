package com.sdia.conferenceservice.clients;

import com.sdia.conferenceservice.model.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteClient {
    @GetMapping("/api/keynotes/{id}")
    Keynote getKeynoteById(@PathVariable("id") Long id);

    @GetMapping("/api/keynotes")
    List<Keynote> getAllKeynotes();
}

