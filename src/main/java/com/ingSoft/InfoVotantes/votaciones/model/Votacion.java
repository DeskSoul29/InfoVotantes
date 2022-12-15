package com.ingSoft.InfoVotantes.votaciones.model;

import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "votaciones")
public class Votacion {
    @Id
    private String id;
    @DBRef
    private Candidato candidatoId;
    private Date date;
}
