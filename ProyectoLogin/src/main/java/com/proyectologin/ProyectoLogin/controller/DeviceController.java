package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import com.proyectologin.ProyectoLogin.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService service;
    private final UsuarioRepository usuarioRepository;

    // LISTAR TODOS (ADMIN)
    @GetMapping
    public List<Device> getAll() {
        return service.findAll();
    }

    // LISTAR SOLO DEL USUARIO LOGUEADO
    @GetMapping("/user/{userId}")
    public List<Device> getByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    // CREAR DEVICE PARA UN USUARIO
    @PostMapping("/{userId}")
    public Device create(@PathVariable Long userId, @RequestBody Device device) {

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        device.setUser(user); // ← asignación del usuario
        return service.save(device);
    }

    // BORRAR
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
