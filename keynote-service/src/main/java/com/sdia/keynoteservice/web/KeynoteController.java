package com.sdia.keynoteservice.web;

import com.sdia.keynoteservice.dtos.KeynoteRequestDTO;
import com.sdia.keynoteservice.dtos.KeynoteResponseDTO;
import com.sdia.keynoteservice.services.KeynoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@RequiredArgsConstructor
public class KeynoteController {

    private final KeynoteService keynoteService;

    @GetMapping
    public List<KeynoteResponseDTO> getAllKeynotes() {
        return keynoteService.findAll();
    }

    @GetMapping("/{id}")
    public KeynoteResponseDTO getKeynoteById(@PathVariable Long id) {
        return keynoteService.findById(id);
    }

    @PostMapping
    public KeynoteResponseDTO saveKeynote(@RequestBody KeynoteRequestDTO keynoteRequestDTO) {
        return keynoteService.save(keynoteRequestDTO);
    }

    @PutMapping("/{id}")
    public KeynoteResponseDTO updateKeynote(@PathVariable Long id, @RequestBody KeynoteRequestDTO keynoteRequestDTO) {
        return keynoteService.update(id, keynoteRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteKeynote(@PathVariable Long id) {
        keynoteService.delete(id);
    }
}
