package com.proyectologin.ProyectoLogin.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria; // notebook, mouse, teclado, etc.
    private String marca;
    private String modelo;
    private String serie;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;
}

