package com.ingSoft.InfoVotantes.votaciones.controller;

import com.ingSoft.InfoVotantes.votaciones.dto.VotacionDTO;
import com.ingSoft.InfoVotantes.votaciones.model.Votacion;
import com.ingSoft.InfoVotantes.votaciones.service.VotacionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/votar")
public class VotacionController {
    private final VotacionService votacionService;

    public VotacionController(VotacionService votacionService) {
        this.votacionService = votacionService;
    }

    @PostMapping
    public Object add(@Validated @RequestBody VotacionDTO votacionDto) {
        return votacionService.add(votacionDto);
    }

    @GetMapping("/detail/{id}")
    public Object readVoto(@PathVariable String id) {
        return votacionService.readVoto(id);
    }

    @GetMapping
    public List<Votacion> getAll() {
        return votacionService.getAll();
    }
}

