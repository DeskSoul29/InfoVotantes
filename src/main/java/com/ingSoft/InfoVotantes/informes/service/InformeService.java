package com.ingSoft.InfoVotantes.informes.service;

import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformeService {
    List<Votacion> getAll();
}

