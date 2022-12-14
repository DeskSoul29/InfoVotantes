package com.ingSoft.InfoVotantes.ciudadano.service;

import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CiudadanoService {
    Object add(CiudadanoDTO ciudadanoDto);
    List<CiudadanoDTO> getAll();
    Object update(int cedula, CiudadanoDTO ciudadanoDto);
    Object delete(int cedula);
    Object readCiudadano(int cedula);
}
