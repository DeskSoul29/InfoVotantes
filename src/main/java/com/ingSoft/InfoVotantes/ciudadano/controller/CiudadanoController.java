package com.ingSoft.InfoVotantes.ciudadano.controller;

import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.ciudadano.service.CiudadanoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ciudadano")
public class CiudadanoController {
    private final CiudadanoService ciudadanoService;

    public CiudadanoController(CiudadanoService ciudadanoService) {
        this.ciudadanoService = ciudadanoService;
    }

    @GetMapping
    public List<CiudadanoDTO> getAll() {
        return ciudadanoService.getAll();
    }

    @PostMapping
    public Object add(@Validated @RequestBody CiudadanoDTO ciudadanoDto) {
        return ciudadanoService.add(ciudadanoDto);
    }

    @GetMapping("/detail/{id}")
    public Object readCiudadano(@PathVariable int id) {
        return ciudadanoService.readCiudadano(id);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable int id, @RequestBody CiudadanoDTO ciudadanoDto) {
        return ciudadanoService.update(id, ciudadanoDto);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        return ciudadanoService.delete(id);
    }
}
