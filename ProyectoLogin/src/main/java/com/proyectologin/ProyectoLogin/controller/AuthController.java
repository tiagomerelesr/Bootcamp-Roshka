package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request,
                                               HttpServletRequest http) {

        // EL SERVICE YA:
        // - valida usuario
        // - valida contraseña
        // - genera token
        // - registra dispositivo automático
        // - devuelve LoginResponse completo

        LoginResponse response = authService.login(request, http);

        return ResponseEntity.ok(response);
    }
}
