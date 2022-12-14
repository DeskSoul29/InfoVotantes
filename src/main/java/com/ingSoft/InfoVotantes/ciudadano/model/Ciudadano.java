package com.ingSoft.InfoVotantes.ciudadano.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "ciudadano")
public class Ciudadano {
    @Id
    private Integer id;
    private String nombres;
    private String apellidos;
    private String municipionacimiento;
    private Date fechanacimiento;
    private String municipioresidencia;
    private String pais;
    private String lugarvotacion;
    private String departamentoresidencia;
    private Boolean voto;
}
