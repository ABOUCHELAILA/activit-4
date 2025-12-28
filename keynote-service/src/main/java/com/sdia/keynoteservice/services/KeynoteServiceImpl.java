package com.sdia.keynoteservice.services;

import com.sdia.keynoteservice.dtos.KeynoteRequestDTO;
import com.sdia.keynoteservice.dtos.KeynoteResponseDTO;
import com.sdia.keynoteservice.entities.Keynote;
import com.sdia.keynoteservice.mappers.KeynoteMapper;
import com.sdia.keynoteservice.repositories.KeynoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class KeynoteServiceImpl implements KeynoteService {

    private final KeynoteRepository keynoteRepository;
    private final KeynoteMapper keynoteMapper;

    @Override
    public KeynoteResponseDTO save(KeynoteRequestDTO keynoteRequestDTO) {
        Keynote keynote = keynoteMapper.fromKeynoteRequestDTO(keynoteRequestDTO);
        Keynote savedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.fromKeynote(savedKeynote);
    }

    @Override
    public KeynoteResponseDTO findById(Long id) {
        Keynote keynote = keynoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keynote not found"));
        return keynoteMapper.fromKeynote(keynote);
    }

    @Override
    public List<KeynoteResponseDTO> findAll() {
        return keynoteRepository.findAll().stream()
                .map(keynoteMapper::fromKeynote)
                .collect(Collectors.toList());
    }

    @Override
    public KeynoteResponseDTO update(Long id, KeynoteRequestDTO keynoteRequestDTO) {
        Keynote keynote = keynoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Keynote not found"));
        if(keynoteRequestDTO.getNom() != null) keynote.setNom(keynoteRequestDTO.getNom());
        if(keynoteRequestDTO.getPrenom() != null) keynote.setPrenom(keynoteRequestDTO.getPrenom());
        if(keynoteRequestDTO.getEmail() != null) keynote.setEmail(keynoteRequestDTO.getEmail());
        if(keynoteRequestDTO.getFonction() != null) keynote.setFonction(keynoteRequestDTO.getFonction());
        Keynote updatedKeynote = keynoteRepository.save(keynote);
        return keynoteMapper.fromKeynote(updatedKeynote);
    }

    @Override
    public void delete(Long id) {
        keynoteRepository.deleteById(id);
    }
}
