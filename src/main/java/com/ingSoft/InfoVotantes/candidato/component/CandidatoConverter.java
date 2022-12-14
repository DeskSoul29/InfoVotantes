package com.ingSoft.InfoVotantes.candidato.component;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import lombok.var;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CandidatoConverter {
    public CandidatoDTO modelToDto(Candidato candidato){
        var candidatoDto= new CandidatoDTO();
        BeanUtils.copyProperties(candidato, candidatoDto);
        return candidatoDto;
    }
    public CandidatoDTO optionalModelToDto(Optional<Candidato> candidato){
        var candidatoDto= new CandidatoDTO();
        BeanUtils.copyProperties(candidato, candidatoDto);
        return candidatoDto;
    }

    public List<CandidatoDTO> listModelToDto(List<Candidato> candidatos){
        List<CandidatoDTO> candidatoDtos= new ArrayList<>();
        for (Candidato candidato: candidatos) {
            candidatoDtos.add(modelToDto(candidato));
        }
        return candidatoDtos;
    }

    public Candidato dtoToModel(CandidatoDTO candidatoDto){
        var candidato= new Candidato();
        BeanUtils.copyProperties(candidatoDto, candidato);
        return candidato;
    }
}
