package com.proyectologin.ProyectoLogin.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Device {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String browser;
        private String os;
        private String deviceType;
        private String ipAddress;
        private LocalDateTime lastLogin;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private Usuario user;
}

