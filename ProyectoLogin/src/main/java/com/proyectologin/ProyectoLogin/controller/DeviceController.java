package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.DeviceRepository;
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
    private final DeviceRepository repo;

    // LISTAR TODOS (ADMIN)
    @GetMapping
    public List<Device> getAll() {
        return service.findAll();
    }

    // LISTAR DISPOSITIVOS DE UN USUARIO (POR ID)
    @GetMapping("/user/id/{id}")
    public List<Device> getByUserId(@PathVariable Long id) {

        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return service.findByUser(user);
    }

    // CREAR / ACTUALIZAR DISPOSITIVO (LOGIN AUTOMÁTICO)
    @PostMapping("/{userId}")
    public Device create(@PathVariable Long userId, @RequestBody Device device) {

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return service.registrarOActualizar(device, user);
    }

    // BORRAR DISPOSITIVO
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
