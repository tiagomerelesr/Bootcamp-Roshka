package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.dto.LoginRequest;
import com.proyectologin.ProyectoLogin.dto.LoginResponse;
import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
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
    private final DeviceService deviceService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

    public LoginResponse login(LoginRequest request, HttpServletRequest servletRequest) {

        // acá validamos el usuario
        Usuario user = usuarioRepository.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // generamos el token
        String token = jwtService.generateToken(user);

        // detectamos los datos del dispositivo
        String ua = servletRequest.getHeader("User-Agent");

        Device nuevo = new Device();
        nuevo.setBrowser(getBrowser(ua));
        nuevo.setOs(getOs(ua));
        nuevo.setDeviceType(getDeviceType(ua));
        nuevo.setIpAddress(servletRequest.getRemoteAddr());
        nuevo.setLastLogin(LocalDateTime.now());

        nuevo.setUser(user);
        nuevo.setActive(true);

        // registrar/reactivar el dispositovo (activo/no activo)
        deviceService.registrarOActualizar(nuevo, user);

        // respuesta
        LoginResponse response = new LoginResponse();
        response.setUsuario(user.getUsuario());
        response.setRol(user.getRol());
        response.setToken(token);
        response.setId(user.getId());

        return response;
    }

    // helper

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
        if (ua.contains("Mobi") || ua.contains("Android") || ua.contains("iPhone"))
            return "Mobile";
        if (ua.contains("iPad") || ua.contains("Tablet"))
            return "Tablet";
        return "Desktop";
    }

    public String hashPassword(String pwd) {
        return encoder.encode(pwd);
    }
}
