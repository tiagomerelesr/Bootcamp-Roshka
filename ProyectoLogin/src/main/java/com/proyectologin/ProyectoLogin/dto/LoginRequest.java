package com.proyectologin.ProyectoLogin.dto;

import lombok.Data;

//Esta es la info que solicita al usariofinal para ingresar

@Data
public class LoginRequest {
    private String email;
    private String password;
}
