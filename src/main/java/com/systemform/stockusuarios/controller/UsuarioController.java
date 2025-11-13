package com.systemform.stockusuarios.controller;

import com.systemform.stockusuarios.model.Stock;
import com.systemform.stockusuarios.model.Usuario;
import com.systemform.stockusuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> updateUsuario = usuarioService.actualizarUsuario(id, usuarioDetails);
        if (updateUsuario.isPresent()) {
            return ResponseEntity.ok(updateUsuario.get());
        } else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{usuarioId}/asignar-stock/{stockId}")
    public ResponseEntity<Usuario> asignarStock(
            @PathVariable Long usuarioId,
            @PathVariable Long stockId
    ){
        Optional<Usuario> usuarioOpt = usuarioService.asignarStock(usuarioId, stockId);
        if(usuarioOpt.isPresent()){
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

