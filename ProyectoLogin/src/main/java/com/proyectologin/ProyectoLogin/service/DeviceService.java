package com.proyectologin.ProyectoLogin.service;

import com.proyectologin.ProyectoLogin.model.Device;
import com.proyectologin.ProyectoLogin.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repo;

    public List<Device> findAll() {
        return repo.findAll();
    }

    public List<Device> findByUserId(Long id) {
        return repo.findByUserId(id);
    }

    public Device save(Device d) {
        return repo.save(d);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}


