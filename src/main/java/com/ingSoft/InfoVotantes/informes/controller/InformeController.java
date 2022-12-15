package com.ingSoft.InfoVotantes.informes.controller;

import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/informes")
public class InformeController {
    //@Autowired
    //private InformeService informeService;

    @GetMapping
    public List<Votacion> getAll() {
        return null;
        //return informeService.getAll();
    }
}
