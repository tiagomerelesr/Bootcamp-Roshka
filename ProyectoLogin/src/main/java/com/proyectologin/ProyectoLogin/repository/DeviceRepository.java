package com.proyectologin.ProyectoLogin.repository;

import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUserId(Long userId);
    List<Device> findByUser(Usuario user);
    List<Device> findByUserIdAndActiveTrue(Long userId);

    Optional<Device> findByUserIdAndIpAddressAndDeviceType(Long userId, String ipAddress, String deviceType);

    Optional<Device> findByUserIdAndIpAddress(Long userId, String ipAddress);
}

