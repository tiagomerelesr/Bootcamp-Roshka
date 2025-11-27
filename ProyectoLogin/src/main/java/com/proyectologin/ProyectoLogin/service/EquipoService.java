package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.model.Equipo;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository repo;

    public List<Equipo> findAll() {
        return repo.findAll();
    }

    public Equipo findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    public List<Equipo> findByUser(Usuario user) {
        return repo.findByUser(user);
    }

    public Equipo save(Equipo e) {
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long count() {
        return repo.count();
    }
}
