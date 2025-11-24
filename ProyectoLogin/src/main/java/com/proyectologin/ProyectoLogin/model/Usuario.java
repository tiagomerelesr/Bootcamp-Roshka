package com.proyectologin.ProyectoLogin.model;

import jakarta.persistence.*;
import lombok.*;

//Representa un usuario en la base de datos.
//Este usuario será utilizado para autenticación y roles.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String rol;

}
