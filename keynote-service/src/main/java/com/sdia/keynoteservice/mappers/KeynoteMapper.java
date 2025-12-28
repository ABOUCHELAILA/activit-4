package com.sdia.keynoteservice.mappers;

import com.sdia.keynoteservice.dtos.KeynoteRequestDTO;
import com.sdia.keynoteservice.dtos.KeynoteResponseDTO;
import com.sdia.keynoteservice.entities.Keynote;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class KeynoteMapper {

    public Keynote fromKeynoteRequestDTO(KeynoteRequestDTO keynoteRequestDTO) {
        Keynote keynote = new Keynote();
        BeanUtils.copyProperties(keynoteRequestDTO, keynote);
        return keynote;
    }

    public KeynoteResponseDTO fromKeynote(Keynote keynote) {
        KeynoteResponseDTO keynoteResponseDTO = new KeynoteResponseDTO();
        BeanUtils.copyProperties(keynote, keynoteResponseDTO);
        return keynoteResponseDTO;
    }
}
