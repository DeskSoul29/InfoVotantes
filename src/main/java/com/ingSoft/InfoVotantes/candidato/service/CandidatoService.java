package com.ingSoft.InfoVotantes.candidato.service;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatoService {
    Object add(CandidatoDTO candidatoDto);
    List<CandidatoDTO> getAll();
    CandidatoDTO readCandidato(int id);
    Object update(String cedula, CandidatoDTO candidatoDto);
    void delete(String id);
}
