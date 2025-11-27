package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
import com.proyectologin.ProyectoLogin.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repo;

    public List<Device> findAll() {
        return repo.findAll();
    }

    public List<Device> findByUser(Usuario user) {
        return repo.findByUser(user);
    }

    public Device save(Device d) {
        return repo.save(d);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // DESACTIVAR TODOS LOS DISPOSITIVOS DEL USUARIO
    public void desactivarDispositivos(Long userId) {
        List<Device> activos = repo.findByUserIdAndActiveTrue(userId);
        activos.forEach(d -> d.setActive(false));
        repo.saveAll(activos);
    }

    public Device registrarOActualizar(Device nuevo, Usuario user) {

        // Normalizar deviceType (evita duplicados por mayúsculas/minúsculas)
        String type = nuevo.getDeviceType().toLowerCase();
        nuevo.setDeviceType(type);

        return repo.findByUserIdAndIpAddressAndDeviceType(
                        user.getId(),
                        nuevo.getIpAddress(),
                        type
                )
                .map(existente -> {
                    // ACTUALIZAR
                    existente.setLastLogin(LocalDateTime.now());
                    existente.setActive(true);
                    existente.setBrowser(nuevo.getBrowser());
                    existente.setOs(nuevo.getOs());
                    existente.setDeviceType(type);
                    return repo.save(existente);
                })
                .orElseGet(() -> {
                    // NUEVO
                    nuevo.setUser(user);
                    nuevo.setActive(true);
                    nuevo.setLastLogin(LocalDateTime.now());
                    return repo.save(nuevo);
                });
    }

}


