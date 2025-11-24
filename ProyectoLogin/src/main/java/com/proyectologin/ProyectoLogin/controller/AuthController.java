package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import com.proyectologin.ProyectoLogin.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor   // crea constructor para todos los "final"
public class AuthController {

    // Servicio encargado del LOGIN (valida usuario + contraseña y genera JWT)
    private final AuthService authService;

    // Repositorio para guardar / buscar usuarios
    private final UsuarioRepository repo;

    //  Necesario para generar hashes de contraseña
    private final BCryptPasswordEncoder encoder;


    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }


    //  REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario user) {

        // 1) Validar si ya existe el email
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        // 2) Encriptar password antes de guardar
        user.setPassword(encoder.encode(user.getPassword()));

        // 3) Guardar en base de datos
        repo.save(user);

        return ResponseEntity.ok("Usuario creado correctamente");
    }


    //  GENERAR HASH (para probar contraseñas en Postman)
    @PostMapping("/hash")
    public String generarHash(@RequestBody String pass){
        return encoder.encode(pass);
    }

}
