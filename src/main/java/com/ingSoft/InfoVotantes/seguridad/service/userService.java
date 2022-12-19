package com.ingSoft.InfoVotantes.seguridad.service;

import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import org.springframework.stereotype.Service;

@Service
public interface userService {
    Object authLogin(CiudadanoDTO ciudadanoDTO);
}
