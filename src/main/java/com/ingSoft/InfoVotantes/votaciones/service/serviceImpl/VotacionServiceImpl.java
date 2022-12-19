package com.ingSoft.InfoVotantes.votaciones.service.serviceImpl;

import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import com.ingSoft.InfoVotantes.candidato.repository.CandidatoRepository;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;
import com.ingSoft.InfoVotantes.votaciones.component.VotacionConverter;
import com.ingSoft.InfoVotantes.votaciones.dto.VotacionDTO;
import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import com.ingSoft.InfoVotantes.votaciones.repository.VotacionRepository;
import com.ingSoft.InfoVotantes.votaciones.service.VotacionService;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VotacionServiceImpl implements VotacionService {
    private final VotacionRepository votacionRepository;
    private final VotacionConverter votacionConverter;
    private final CiudadanoRepository ciudadanoRepository;
    private final CandidatoRepository candidatoRepository;

    public VotacionServiceImpl(CandidatoRepository candidatoRepository, CiudadanoRepository ciudadanoRepository, VotacionConverter votacionConverter, VotacionRepository votacionRepository) {
        this.candidatoRepository = candidatoRepository;
        this.ciudadanoRepository = ciudadanoRepository;
        this.votacionConverter = votacionConverter;
        this.votacionRepository = votacionRepository;
    }


    @Override
    public Object add(VotacionDTO votacionDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(votacionDto.getCedCiudadano());
        if(personaCiudadano.isPresent()){
            Optional<Candidato> personaCandidato = candidatoRepository.findById(votacionDto.getCandidatoId());
            if(personaCandidato.isPresent()){
                if(!personaCiudadano.get().getVoto()){
                    var voto = votacionConverter.dtoToModel(votacionDto);
                    voto.setCandidatoId(personaCandidato.get());
                    voto.setNombreCand(personaCandidato.get().getNombre());
                    voto.setDate(new Date());
                    voto.setPais((personaCiudadano.get().getPais()));
                    voto.setLugarvotacion((personaCiudadano.get().getLugarvotacion()));
                    voto.setDepartamento((personaCiudadano.get().getDepartamentoresidencia()));

                    Ciudadano ciudadano = personaCiudadano.get();
                    ciudadano.setVoto(true);
                    ciudadanoRepository.save(ciudadano);

                    return votacionRepository.save(voto);
                }
                return new ResponseEntity<>("The Ciudadano already voted", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("The Candidato isn't registered", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("The Ciudadano isn't registered", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Object readVoto(String id) {
        Optional<Votacion> voto = votacionRepository.findById(id);

        if(voto.isPresent()){
            return voto.get();
        }
        return new ResponseEntity<>("Voto not present in the database", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<Votacion> getAll() {
        return votacionRepository.findAll();
    }
}
