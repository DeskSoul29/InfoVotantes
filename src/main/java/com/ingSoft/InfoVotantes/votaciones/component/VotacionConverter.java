package com.ingSoft.InfoVotantes.votaciones.component;

import com.ingSoft.InfoVotantes.votaciones.dto.VotacionDTO;
import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VotacionConverter {

    public Votacion dtoToModel(VotacionDTO votacionDto) {
        var votacion = new Votacion();
        BeanUtils.copyProperties(votacionDto, votacion);
        return votacion;
    }
}