package com.ingSoft.InfoVotantes.votaciones.service;

import com.ingSoft.InfoVotantes.votaciones.dto.VotacionDTO;
import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotacionService {
    Object add(VotacionDTO votacionDto);
    Object readVoto(String id);
    List<Votacion> getAll();
}
