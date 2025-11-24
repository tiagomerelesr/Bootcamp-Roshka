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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRol())  // ADMIN / USER
                .build();
    }
}
