package com.ingSoft.InfoVotantes.ciudadano.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CiudadanoDTO {
    private Integer id;
    private String apellidos;
    private String municipionacimiento;
    private Date fechanacimiento;
    private String municipioresidencia;
    private String nombres;
    private String pais;
    private String lugarvotacion;
    private String departamentoresidencia;
    private Boolean voto;
}
