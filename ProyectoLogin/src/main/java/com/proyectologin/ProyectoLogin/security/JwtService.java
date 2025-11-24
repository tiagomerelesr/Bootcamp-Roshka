package com.proyectologin.ProyectoLogin.security;


import com.proyectologin.ProyectoLogin.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

//Clase encargada de crear y valida tokens
@Service
public class JwtService {

        //Clave secreta interna
        private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        //Genera un token
        public String generateToken(Usuario user){
            return Jwts.builder()
                    .setSubject(user.getEmail())           // usuario
                    .claim("rol", user.getRol())           // rol
                    .setIssuedAt(new Date())               // fecha de creación
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // válido por 24h
                    .signWith(key)
                    .compact();
        }

    //Extrae el email desde un token JWT
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
