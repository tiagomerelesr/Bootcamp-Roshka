package com.proyectologin.ProyectoLogin.controller;

import com.proyectologin.ProyectoLogin.model.Equipo;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import com.proyectologin.ProyectoLogin.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;
    private final UsuarioRepository usuarioRepository;

    // LISTAR TODOS LOS EQUIPOS
    @GetMapping
    public List<Equipo> getAll() {
        return service.findAll();
    }

    // CANTIDAD TOTAL DE EQUIPOS
    @GetMapping("/count")
    public long count() {
        return service.count();
    }


    // LISTAR EQUIPOS POR USUARIO
    @GetMapping("/user/{userId}")
    public List<Equipo> getByUser(@PathVariable Long userId) {

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return service.findByUser(user);
    }


    // CREAR Y ASIGNAR EQUIPO A USUARIO
    @PostMapping("/{userId}")
    public Equipo create(@PathVariable Long userId, @RequestBody Equipo equipo) {

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        equipo.setUser(user);

        // si no envían cantidad, poner 1 por defecto
        if (equipo.getCantidad() == null) {
            equipo.setCantidad(1);
        }

        return service.save(equipo);
    }

    // EDITAR UN EQUIPO
    @PutMapping("/{id}")
    public Equipo update(@PathVariable Long id, @RequestBody Equipo req) {

        Equipo equipo = service.findById(id);

        equipo.setCategoria(req.getCategoria());
        equipo.setMarca(req.getMarca());
        equipo.setModelo(req.getModelo());
        equipo.setSerie(req.getSerie());
        equipo.setCantidad(req.getCantidad());

        // Si el update asigna otro usuario
        if (req.getUser() != null && req.getUser().getId() != null) {

            Usuario user = usuarioRepository.findById(req.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            equipo.setUser(user);
        }

        return service.save(equipo);
    }

    // ELIMINAR EQUIPO
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
