package com.ingSoft.InfoVotantes.votaciones.repository;

import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VotacionRepository extends MongoRepository<Votacion, String> {

    Optional<Votacion> findById(String id);
}
