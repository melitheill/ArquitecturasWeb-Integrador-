package org.grupo14.mcsvviaje.service;

import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    public List<Viaje> getAll(){
        return viajeRepository.findAll();
    }

    public Viaje save(Viaje viaje){
        return viajeRepository.save(viaje);
    }

    public Viaje delete(Viaje viaje){
        viajeRepository.delete(viaje);
        return viaje;
    }

    public Viaje findById(Long id){
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje update(Viaje viaje){
        return viajeRepository.save(viaje);
    }
}
