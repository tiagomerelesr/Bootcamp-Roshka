package com.systemform.stockusuarios.service;

import com.systemform.stockusuarios.model.Stock;
import com.systemform.stockusuarios.model.Usuario;
import com.systemform.stockusuarios.repository.UsuarioRepository;
import com.systemform.stockusuarios.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- IMPORTANTE
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StockRepository stockRepository; // <-- NECESARIO para asignar stock

    @Autowired
    private PasswordEncoder passwordEncoder; // <-- para encriptar contraseña

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // esto es para Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    // Actualizar usuario
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuarioDetails) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            usuarioRepository.save(usuario);
        }

        return optionalUsuario;
    }

    // Eliminar usuario
    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //  esto para asignar stock a un usuario (solo ADMIN podrá usarlo)
    public Optional<Usuario> asignarStock(Long usuarioId, Long stockId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Stock> stockOpt = stockRepository.findById(stockId);

        if (usuarioOpt.isPresent() && stockOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Stock stock = stockOpt.get();

            stock.setUsuario(usuario); // Asignamos el stock al usuario
            stockRepository.save(stock);

            return Optional.of(usuario);
        }

        return Optional.empty();
    }
}

