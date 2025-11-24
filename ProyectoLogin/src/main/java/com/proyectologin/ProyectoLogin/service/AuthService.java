package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import com.proyectologin.ProyectoLogin.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//Servicio encargado de:1) Buscar usuario por email2) Validar contraseña (BCrypt)3) Generar el token JWT

@Service
@RequiredArgsConstructor   // Crea el constructor para los atributos final
public class AuthService {

    private final UsuarioRepository repo;       // Acceso a la BD para buscar el usuario
    private final JwtService jwt;               // Servicio para generar el token JWT
    private final BCryptPasswordEncoder encoder; // Para comparar contraseñas encriptadas


    //Lógica principal del login
    public LoginResponse login(LoginRequest req){

        // Buscar usuario por email
        Usuario user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar contraseña encriptada (BCrypt)
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar token JWT con email + rol
        String token = jwt.generateToken(user);

        // Devolver token al controller
        return new LoginResponse(token);
    }
}

