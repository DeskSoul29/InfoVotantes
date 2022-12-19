package com.ingSoft.InfoVotantes.ciudadano.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CiudadanoDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String municipionacimiento;
    private Date fechanacimiento;
    private String pais;
    private String departamentoresidencia;
    private String municipioresidencia;
    private String lugarvotacion;
    private String correo;
    private String pass;
    private Long phone;
    private Boolean voto;
    private String roles;
}
