package com.ingSoft.InfoVotantes.candidato.dto;

import lombok.Data;

@Data
public class CandidatoDTO {
    private String id;
    private String nombre;
    private String eslogan;
    private String partidoPolitico;
    private String propuesta;
    private Integer cedCiudadano;
}
