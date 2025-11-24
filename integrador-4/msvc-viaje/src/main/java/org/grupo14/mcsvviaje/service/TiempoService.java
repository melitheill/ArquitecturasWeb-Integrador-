package org.grupo14.mcsvviaje.service;

import lombok.RequiredArgsConstructor;
import org.grupo14.mcsvviaje.entity.Tiempo;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.repository.TiempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TiempoService {

    private final TiempoRepository tiempoRepository;

    public Tiempo save(Tiempo tiempo){
        return tiempoRepository.save(tiempo);
    }
}
