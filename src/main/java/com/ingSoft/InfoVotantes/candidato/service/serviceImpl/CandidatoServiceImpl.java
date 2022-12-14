package com.ingSoft.InfoVotantes.candidato.service.serviceImpl;

import com.ingSoft.InfoVotantes.candidato.component.CandidatoConverter;
import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.repository.CandidatoRepository;
import com.ingSoft.InfoVotantes.candidato.service.CandidatoService;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.var;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatoServiceImpl implements CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private CandidatoConverter candidatoConverter;
    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Override
    public Object add(CandidatoDTO candidatoDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(Integer.parseInt(candidatoDto.getCed_ciudadano()));
        if(personaCiudadano.isPresent()){
            var candidato = candidatoConverter.dtoToModel(candidatoDto);
            candidato.setCed_ciudadano(personaCiudadano.get());
            return candidatoRepository.save(candidato);
        }
        return new ResponseEntity<>("The User isn't registered", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<CandidatoDTO> getAll() {
        var candidatos = candidatoRepository.findAll();
        return candidatoConverter.listModelToDto(candidatos);
    }

    @Override
    public CandidatoDTO readCandidato(int id) {
        var cliente = candidatoRepository.getCandidatoById(id);
        return candidatoConverter.modelToDto(cliente);
    }

    @Override
    public Object update(String cedula, CandidatoDTO candidatoDto) {
        return null;
    }

    @Override
    public void delete(String id) {
        candidatoRepository.deleteById(id);
    }
}
