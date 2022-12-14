package com.ingSoft.InfoVotantes.candidato.controller;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidato")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public List<CandidatoDTO> getAll() {
        return candidatoService.getAll();
    }

    @PostMapping
    public Object add(@Validated @RequestBody CandidatoDTO candidatoDto) {
        return candidatoService.add(candidatoDto);
    }

    @GetMapping("/detail/{id}")
    public CandidatoDTO readCandidato(@PathVariable int id) {
        return candidatoService.readCandidato(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id, @RequestBody CandidatoDTO candidatoDto) {
        return candidatoService.update(id, candidatoDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        candidatoService.delete(id);
    }
}
