package com.ingSoft.InfoVotantes.ciudadano.component;

import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.ciudadano.model.Ciudadano;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CiudadanoConverter {
    public CiudadanoDTO modelToDto(Ciudadano ciudadano){
        var ciudadanoDto= new CiudadanoDTO();
        BeanUtils.copyProperties(ciudadano, ciudadanoDto);
        return ciudadanoDto;
    }

    public List<CiudadanoDTO> listModelToDto(List<Ciudadano> ciudadanos){
        List<CiudadanoDTO> ciudadanoDto= new ArrayList<>();
        for (Ciudadano ciudadano: ciudadanos) {
            ciudadanoDto.add(modelToDto(ciudadano));
        }
        return ciudadanoDto;
    }

    public Ciudadano dtoToModel(CiudadanoDTO ciudadanoDto){
        var ciudadano= new Ciudadano();
        BeanUtils.copyProperties(ciudadanoDto, ciudadano);
        return ciudadano;
    }
}
