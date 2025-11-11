package org.grupo14.mcsvviaje.service;

import org.grupo14.mcsvviaje.entity.Tiempo;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.repository.TiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiempoService {
    @Autowired
    TiempoRepository tiempoRepository;

    public List<Tiempo> getAll(){
        return tiempoRepository.findAll();
    }

    public Tiempo save(Tiempo tiempo){

        return tiempoRepository.save(tiempo);
    }

    public Tiempo delete(Tiempo tiempo){
        tiempoRepository.delete(tiempo);
        return tiempo;
    }

    public Tiempo findById(Long id){
        return tiempoRepository.findById(id).orElse(null);
    }

    public Tiempo update(Tiempo tiempo){
        return tiempoRepository.save(tiempo);
    }
}
