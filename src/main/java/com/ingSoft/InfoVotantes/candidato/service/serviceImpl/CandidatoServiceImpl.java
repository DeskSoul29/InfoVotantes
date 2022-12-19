package com.ingSoft.InfoVotantes.candidato.service.serviceImpl;

import com.ingSoft.InfoVotantes.candidato.component.CandidatoConverter;
import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import com.ingSoft.InfoVotantes.candidato.repository.CandidatoRepository;
import com.ingSoft.InfoVotantes.candidato.service.CandidatoService;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoServiceImpl implements CandidatoService {
    private final CandidatoRepository candidatoRepository;
    private final CandidatoConverter candidatoConverter;
    private final CiudadanoRepository ciudadanoRepository;

    public CandidatoServiceImpl(CandidatoRepository candidatoRepository, CandidatoConverter candidatoConverter, CiudadanoRepository ciudadanoRepository) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoConverter = candidatoConverter;
        this.ciudadanoRepository = ciudadanoRepository;
    }

    @Override
    public Object add(CandidatoDTO candidatoDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(candidatoDto.getCedCiudadano());
        if(personaCiudadano.isPresent()){
            Optional<Candidato> personaCandidato = candidatoRepository.findByCedCiudadano(candidatoDto.getCedCiudadano());
            if(!personaCandidato.isPresent()){
                if(personaCiudadano.get().getCorreo() != null){
                    var candidato = candidatoConverter.dtoToModel(candidatoDto);
                    candidato.setCedCiudadano(personaCiudadano.get());
                    return candidatoRepository.save(candidato);
                }
                return new ResponseEntity<>("The Ciudadano does not have mail", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("The Candidato is registered", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("The Ciudadano isn't registered", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<Candidato> getAll() {
        return candidatoRepository.findAll();
    }

    @Override
    public Object readCandidato(String id) {
        Optional<Candidato> personaCandidato = candidatoRepository.findById(id);
        if(personaCandidato.isPresent()){
            return personaCandidato.get();
        }
        return new ResponseEntity<>("The Candidato isn't registered", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Object update(String id, CandidatoDTO candidatoDto) {
        Optional<Candidato> personaCandidato = candidatoRepository.findById(id);

        if(personaCandidato.isPresent()){
            Candidato candidato = personaCandidato.get();
            candidato.setNombre(candidatoDto.getNombre());
            candidato.setEslogan(candidatoDto.getEslogan());
            candidato.setPropuesta(candidatoDto.getPropuesta());
            candidato.setPartidoPolitico(candidatoDto.getPartidoPolitico());
            return candidatoRepository.save(candidato);
        }
        return new ResponseEntity<>("Cant find any Candidato with the given id", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Object delete(String id) {
        Optional<Candidato> personaCandidato = candidatoRepository.findById(id);
        if(personaCandidato.isPresent()){
            candidatoRepository.deleteById(id);
            return new ResponseEntity<>("User Delete", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Cant find any Candidato with the given id", HttpStatus.UNAUTHORIZED);
    }
}
