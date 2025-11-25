package com.proyectologin.ProyectoLogin.repository;

import com.proyectologin.ProyectoLogin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Buscar por usuario
    Optional<Usuario> findByUsuario(String Usuario);
}
