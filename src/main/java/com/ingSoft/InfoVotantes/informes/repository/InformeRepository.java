package com.ingSoft.InfoVotantes.informes.repository;

import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InformeRepository extends MongoRepository<Votacion, String> {

}
