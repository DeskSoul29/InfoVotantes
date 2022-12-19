package com.ingSoft.InfoVotantes.seguridad.service.serviceImpl;

import com.ingSoft.InfoVotantes.ciudadano.component.CiudadanoConverter;
import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;
import com.ingSoft.InfoVotantes.ciudadano.service.serviceImpl.CiudadanoServiceImpl;
import com.ingSoft.InfoVotantes.seguridad.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userDetailServiceImpl implements userService {
    final
    CiudadanoServiceImpl usuarioService;
    final
    CiudadanoRepository ciudadanoRepository;
    final
    CiudadanoConverter ciudadanoConverter;
    final
    PasswordEncoder passwordEncoder;

    public userDetailServiceImpl(CiudadanoServiceImpl usuarioService, CiudadanoRepository ciudadanoRepository, CiudadanoConverter ciudadanoConverter, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.ciudadanoRepository = ciudadanoRepository;
        this.ciudadanoConverter = ciudadanoConverter;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Object authLogin(CiudadanoDTO ciudadanoDTO) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findByCorreo(ciudadanoDTO.getCorreo());
        if(personaCiudadano.isPresent()){
            System.out.println(passwordEncoder.matches(ciudadanoDTO.getPass(), personaCiudadano.get().getPass()));
            if(passwordEncoder.matches(ciudadanoDTO.getPass(), personaCiudadano.get().getPass()) ){
                return ciudadanoConverter.modelToDto(ciudadanoRepository.getCiudadanoById(personaCiudadano.get().getId()));
            }
            return new ResponseEntity<>("Pass wrong", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Mail not present in the database", HttpStatus.UNAUTHORIZED);
    }


}
