package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UsuarioRepository repo;
    private final BCryptPasswordEncoder encoder;

    // CREATE – Crear usuario (para Postman o frontend)
    @PostMapping
    public Usuario create(@RequestBody Usuario user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // READ – Listar todos los usuarios
    @GetMapping
    public List<Usuario> all() {
        return repo.findAll();
    }

    // UPDATE – Actualizar usuario
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario u) {
        Usuario user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setUsuario(u.getUsuario());
        user.setEmail(u.getEmail());
        user.setRol(u.getRol());

        // Solo actualiza contraseña si viene una nueva
        if (u.getPassword() != null && !u.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(u.getPassword()));
        }

        return repo.save(user);
    }

    // DELETE – Borrar usuario
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
