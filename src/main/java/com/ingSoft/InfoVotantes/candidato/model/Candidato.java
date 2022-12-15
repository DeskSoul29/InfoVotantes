package com.ingSoft.InfoVotantes.candidato.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;

@Getter
@Setter
@Document(collection = "candidato")
public class Candidato {
    @Id
    private String id;

    private String nombre;
    private String eslogan;
    private String partidoPolitico;
    private String propuesta;
    @DBRef
    private Ciudadano cedCiudadano;
}
