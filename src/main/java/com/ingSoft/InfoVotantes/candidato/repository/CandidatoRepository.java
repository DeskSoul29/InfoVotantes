package com.ingSoft.InfoVotantes.candidato.repository;

import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CandidatoRepository extends MongoRepository<Candidato, String> {
    Optional<Candidato> findById(String id);
    void deleteById(String id);
    Candidato getCandidatoById(int id);
}
