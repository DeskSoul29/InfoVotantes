package com.ingSoft.InfoVotantes.candidato.controller;

import com.ingSoft.InfoVotantes.candidato.dto.CandidatoDTO;
import com.ingSoft.InfoVotantes.candidato.model.Candidato;
import com.ingSoft.InfoVotantes.candidato.service.CandidatoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidato")
public class CandidatoController {
    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public List<Candidato> getAll() {
        return candidatoService.getAll();
    }

    @PostMapping
    public Object add(@Validated @RequestBody CandidatoDTO candidatoDto) {
        return candidatoService.add(candidatoDto);
    }

    @GetMapping("/detail/{id}")
    public Object readCandidato(@PathVariable String id) {
        return candidatoService.readCandidato(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id, @RequestBody CandidatoDTO candidatoDto) {
        return candidatoService.update(id, candidatoDto);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id) {
        return candidatoService.delete(id);
    }
}
