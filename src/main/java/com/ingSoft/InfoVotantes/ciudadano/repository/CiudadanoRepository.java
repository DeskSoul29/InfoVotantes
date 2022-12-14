package com.ingSoft.InfoVotantes.ciudadano.repository;

import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CiudadanoRepository extends MongoRepository<Ciudadano, Integer> {
    void deleteById(int id);

    Optional<Ciudadano> findById(int id);

    Ciudadano getCiudadanoById(int id);
}
