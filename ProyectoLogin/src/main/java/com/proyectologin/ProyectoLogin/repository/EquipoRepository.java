package com.proyectologin.ProyectoLogin.repository;

import com.proyectologin.ProyectoLogin.model.Equipo;
import com.proyectologin.ProyectoLogin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByUser(Usuario user);

    List<Equipo> findByUserId(Long userId);
}
