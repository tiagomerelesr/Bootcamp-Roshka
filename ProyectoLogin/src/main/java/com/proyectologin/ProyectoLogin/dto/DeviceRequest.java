package com.proyectologin.ProyectoLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceRequest {
    private String browser;
    private String os;
    private String deviceType;
    private String ipAddress;
}
