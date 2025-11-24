package com.proyectologin.ProyectoLogin.dto;

//Respuesta enviada al frontend: contiene el token JWT.

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

}
