package org.example.msvctarifa.service;

import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TarifaService {

    @Autowired
    TarifaRepository tarifaRepository;

    public List<Tarifa> getAll() {return tarifaRepository.findAll();}

    public Tarifa save(Tarifa tarifa) {return tarifaRepository.save(tarifa);}

    public Tarifa delete(Tarifa tarifa) {
        tarifaRepository.delete(tarifa);
        return tarifa;
    }

    public Tarifa findById(Long id) {return tarifaRepository.findById(id).orElse(null);}

    public Tarifa update(Tarifa tarifa) {return tarifaRepository.save(tarifa);}
}
