package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.DeviceRepository;
import com.proyectologin.ProyectoLogin.repository.UsuarioRepository;
import com.proyectologin.ProyectoLogin.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final DeviceRepository deviceRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

    public LoginResponse login(LoginRequest request, HttpServletRequest servletRequest) {

        Usuario user = usuarioRepository.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(user);

        // Guardar info del dispositivo
        String userAgent = servletRequest.getHeader("User-Agent");

        Device device = new Device();
        device.setBrowser(getBrowser(userAgent));
        device.setOs(getOs(userAgent));
        device.setDeviceType(getDeviceType(userAgent));
        device.setIpAddress(servletRequest.getRemoteAddr());
        device.setLastLogin(LocalDateTime.now());
        device.setUser(user);

        deviceRepository.save(device);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRol(user.getRol());
        response.setUsuario(user.getUsuario());

        return response;
    }

    private String getBrowser(String ua) {
        if (ua == null) return "UNKNOWN";
        if (ua.contains("Chrome")) return "Chrome";
        if (ua.contains("Firefox")) return "Firefox";
        if (ua.contains("Safari") && !ua.contains("Chrome")) return "Safari";
        if (ua.contains("Edge")) return "Edge";
        return "Other";
    }

    private String getOs(String ua) {
        if (ua == null) return "UNKNOWN";
        if (ua.contains("Windows")) return "Windows";
        if (ua.contains("Mac")) return "MacOS";
        if (ua.contains("Linux")) return "Linux";
        if (ua.contains("Android")) return "Android";
        if (ua.contains("iPhone") || ua.contains("iPad")) return "iOS";
        return "Other";
    }

    private String getDeviceType(String ua) {
        if (ua == null) return "UNKNOWN";
        if (ua.contains("Mobi") || ua.contains("Android") || ua.contains("iPhone")) return "Mobile";
        if (ua.contains("iPad") || ua.contains("Tablet")) return "Tablet";
        return "Desktop";
    }

    public String hashPassword(String pwd) {
        return encoder.encode(pwd);
    }
}
