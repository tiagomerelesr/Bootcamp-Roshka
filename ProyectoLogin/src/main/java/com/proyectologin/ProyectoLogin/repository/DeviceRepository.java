package com.proyectologin.ProyectoLogin.repository;

import com.proyectologin.ProyectoLogin.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUserId(Long userId);
}
