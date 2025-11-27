package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.service.AuthService;
import com.proyectologin.ProyectoLogin.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final DeviceService deviceService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest http) {
        LoginResponse response = authService.login(request, http);
        return ResponseEntity.ok(response);
    }

    // para el logout
    @PostMapping("/logout/{userId}")
    public ResponseEntity<Void> logout(@PathVariable Long userId) {
        deviceService.desactivarDispositivos(userId);
        return ResponseEntity.ok().build();
    }
}
