package com.ingSoft.InfoVotantes.ciudadano.service.serviceImpl;

import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import com.ingSoft.InfoVotantes.candidato.repository.CandidatoRepository;
import com.ingSoft.InfoVotantes.ciudadano.component.CiudadanoConverter;
import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import com.ingSoft.InfoVotantes.ciudadano.repository.CiudadanoRepository;
import com.ingSoft.InfoVotantes.ciudadano.service.CiudadanoService;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadanoServiceImpl implements CiudadanoService {

    private final CiudadanoRepository ciudadanoRepository;
    private final CiudadanoConverter ciudadanoConverter;
    private final CandidatoRepository candidatoRepository;
    private final JavaMailSender mailSender;
    final PasswordEncoder passwordEncoder;

    public CiudadanoServiceImpl(CiudadanoRepository ciudadanoRepository, CiudadanoConverter ciudadanoConverter, CandidatoRepository candidatoRepository, JavaMailSender mailSender, PasswordEncoder passwordEncoder) {
        this.ciudadanoRepository = ciudadanoRepository;
        this.ciudadanoConverter = ciudadanoConverter;
        this.candidatoRepository = candidatoRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

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
    public Object readCiudadano(int id) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        if(personaCiudadano.isPresent()){
            return ciudadanoConverter.modelToDto(ciudadanoRepository.getCiudadanoById(id));
        }
        return new ResponseEntity<>("User not present in the database", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public List<CiudadanoDTO> getAll() {
        var ciudadanos = ciudadanoRepository.findAll();
        return ciudadanoConverter.listModelToDto(ciudadanos);
    }

    @Override
    public Object update(int id, CiudadanoDTO ciudadanoDto) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        if(personaCiudadano.isPresent()){
            Ciudadano ciudadano = personaCiudadano.get();
            ciudadano.setNombres(ciudadanoDto.getNombres());
            ciudadano.setApellidos(ciudadanoDto.getApellidos());
            ciudadano.setMunicipionacimiento(ciudadanoDto.getMunicipionacimiento());
            ciudadano.setMunicipioresidencia(ciudadanoDto.getMunicipioresidencia());
            ciudadano.setFechanacimiento(ciudadanoDto.getFechanacimiento());
            ciudadano.setPais(ciudadanoDto.getPais());
            ciudadano.setLugarvotacion(ciudadanoDto.getLugarvotacion());
            ciudadano.setDepartamentoresidencia(ciudadanoDto.getDepartamentoresidencia());
            ciudadano.setCorreo(ciudadanoDto.getCorreo());
            ciudadano.setPhone(ciudadanoDto.getPhone());

            if(ciudadanoDto.getPass() == null){
                String pass = generateRandomPassword(10);
                send(ciudadanoDto.getCorreo(), "Registro Exitoso a InfoVotantes", ciudadanoDto.getNombres(), pass);
                ciudadano.setPass(passwordEncoder.encode(pass));
            }else{
                ciudadano.setPass(passwordEncoder.encode(ciudadanoDto.getPass()));
            }

            return ciudadanoRepository.save(ciudadano);
        }
        return new ResponseEntity<>("Cant find any user with the given id", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Object delete(int id) {
        Optional<Ciudadano> personaCiudadano = ciudadanoRepository.findById(id);

        if(personaCiudadano.isPresent()){
            ciudadanoRepository.deleteById(id);
            Optional<Candidato> personaCandidato = candidatoRepository.findByCedCiudadano(id);
            if(personaCandidato.isPresent()){
                candidatoRepository.deleteByCedCiudadano(id);
            }
            return new ResponseEntity<>("User Delete", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Cant find any user with the given id", HttpStatus.UNAUTHORIZED);
    }

    public void send(String to, String subject, String name, String pass) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mashdez2022@gmail.com");
        message.setTo(to);
        message.setSubject("¡Bienvenido a InfoVotantes!");
        message.setText("¡Hola "+ name +"!, nos complace informarte que has sido registrado en el sistema de InfoVotantes, las credenciales para ingresar son: \n Correo: "+ to +" \n Contraseña: "+ pass);
        mailSender.send(message);
    }

    public static String generateRandomPassword(int len){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

}
