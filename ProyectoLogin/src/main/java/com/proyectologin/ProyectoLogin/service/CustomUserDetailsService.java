package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

        Usuario user = repo.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return User.builder()
                .username(user.getUsuario())   //
                .password(user.getPassword())
                .roles(user.getRol())          // ADMIN / USER
                .build();
    }
}
