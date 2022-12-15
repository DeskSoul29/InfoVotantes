package com.ingSoft.InfoVotantes.votaciones.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VotacionDTO {
    private String id;
    private Integer cedCiudadano;
    private String candidatoId;
    private Date date;
}
