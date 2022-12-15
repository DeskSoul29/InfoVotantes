package com.ingSoft.InfoVotantes.candidato.service;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatoService {
    Object add(CandidatoDTO candidatoDto);
    List<Candidato> getAll();
    Object readCandidato(String id);

    Object update(String id, CandidatoDTO candidatoDto);
    Object delete(String id);
}
