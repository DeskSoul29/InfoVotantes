package com.ingSoft.InfoVotantes.candidato.component;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CandidatoConverter {

    public Candidato dtoToModel(CandidatoDTO candidatoDto){
        var candidato= new Candidato();
        BeanUtils.copyProperties(candidatoDto, candidato);
        return candidato;
    }
}