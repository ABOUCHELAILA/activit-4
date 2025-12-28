package com.sdia.keynoteservice.services;

import com.sdia.keynoteservice.dtos.KeynoteRequestDTO;
import com.sdia.keynoteservice.dtos.KeynoteResponseDTO;

import java.util.List;

public interface KeynoteService {
    KeynoteResponseDTO save(KeynoteRequestDTO keynoteRequestDTO);
    KeynoteResponseDTO findById(Long id);
    List<KeynoteResponseDTO> findAll();
    KeynoteResponseDTO update(Long id, KeynoteRequestDTO keynoteRequestDTO);
    void delete(Long id);
}
