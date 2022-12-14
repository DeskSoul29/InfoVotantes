package com.ingSoft.InfoVotantes.ciudadano.service.serviceImpl;

import com.ingSoft.InfoVotantes.ciudadano.component.CiudadanoConverter;
import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;
import com.ingSoft.InfoVotantes.ciudadano.service.CiudadanoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.var;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadanoServiceImpl implements CiudadanoService {

    @Autowired
    private CiudadanoRepository ciudadanoRepository;
    @Autowired
    private CiudadanoConverter ciudadanoConverter;

    @Override
    public Object add(CiudadanoDTO ciudadanoDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(ciudadanoDto.getId());
        if(!personaCiudadano.isPresent()){
            var ciudadano = ciudadanoConverter.dtoToModel(ciudadanoDto);
            ciudadano.setVoto(false);
            return ciudadanoConverter.modelToDto(ciudadanoRepository.save(ciudadano));
        }
        return new ResponseEntity<>("The User is registered", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<CiudadanoDTO> getAll() {
        var ciudadanos = ciudadanoRepository.findAll();
        return ciudadanoConverter.listModelToDto(ciudadanos);
    }

    @Override
    public Object update(int id, CiudadanoDTO ciudadanoDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        //var cliente = ciudadanoRepository.getCiudadanoById(id);
        if(personaCiudadano.isPresent()){
            Ciudadano ciudadano = personaCiudadano.get();
            ciudadano.setNombres(ciudadanoDto.getNombres());
            ciudadano.setApellidos(ciudadanoDto.getApellidos());
            ciudadano.setMunicipioresidencia(ciudadanoDto.getMunicipioresidencia());
            ciudadano.setFechanacimiento(ciudadanoDto.getFechanacimiento());
            ciudadano.setPais(ciudadanoDto.getPais());
            ciudadano.setLugarvotacion(ciudadanoDto.getLugarvotacion());
            ciudadano.setDepartamentoresidencia(ciudadanoDto.getDepartamentoresidencia());
            return ciudadanoRepository.save(ciudadano);
        }else {
            return new ResponseEntity<>("Cant find any user with the given id", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Object delete(int id) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        if(personaCiudadano.isPresent()){
            ciudadanoRepository.deleteById(id);
            return new ResponseEntity<>("User Delete", HttpStatus.UNAUTHORIZED);
        }else {
            return new ResponseEntity<>("Cant find any user with the given id", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Object readCiudadano(int id) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        if(personaCiudadano.isPresent()){
            return ciudadanoConverter.modelToDto(ciudadanoRepository.getCiudadanoById(id));
        }else {
            return new ResponseEntity<>("User not present in the database", HttpStatus.UNAUTHORIZED);
        }
    }

}
