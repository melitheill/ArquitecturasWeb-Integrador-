package org.example.msvcparada.service;

import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    public List<Parada> getAll() {return paradaRepository.findAll();}

    public Parada save(Parada tarifa) {return paradaRepository.save(tarifa);}

    public Parada delete(Parada tarifa) {
        paradaRepository.delete(tarifa);
        return tarifa;
    }

    public Parada findById(Long id) {return paradaRepository.findById(id).orElse(null);}

    public Parada update(Parada tarifa) {return paradaRepository.save(tarifa);}
}
