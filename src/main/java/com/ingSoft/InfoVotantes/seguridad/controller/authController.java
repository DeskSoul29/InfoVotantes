package com.ingSoft.InfoVotantes.seguridad.controller;

import com.ingSoft.InfoVotantes.ciudadano.dto.CiudadanoDTO;
import com.ingSoft.InfoVotantes.seguridad.service.userService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {
    final
    userService UserService;

    public authController(userService UserService) {
        this.UserService = UserService;
    }

    @PostMapping
    public Object authUser(@Validated @RequestBody CiudadanoDTO ciudadanoDto) {
        return UserService.authLogin(ciudadanoDto);
    }
}
