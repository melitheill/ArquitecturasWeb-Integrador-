package org.example.msvctarifa.service;

import jakarta.transaction.Transactional;
import org.example.msvctarifa.DTO.TarifaDTO;
import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarifaService {

    @Autowired
    TarifaRepository tarifaRepository;

    public List<Tarifa> getAll() {return tarifaRepository.findAll();}
    @Transactional
    public Tarifa save(Tarifa nueva) {
        // Si la nueva tarifa empieza hoy, desactivamos la anterior y activamos esta
        if (nueva.getFechaVigencia() == null || nueva.getFechaVigencia().equals(LocalDate.now())) {
            tarifaRepository.findByActivaTrue().ifPresent(t -> {
                t.desactivar();
                tarifaRepository.save(t);
            });
            nueva.activar();

        } else {
            // Si es para el futuro, la dejamos inactiva hasta esa fecha
            nueva.setActiva(false);
        }
        return tarifaRepository.save(nueva);
    }

    public TarifaDTO obtenerTarifaActiva() {
        Tarifa tarifa= tarifaRepository.findByActivaTrue().get();
        TarifaDTO tarifaDTO = new TarifaDTO(tarifa.getMonto_base(), tarifa.getMonto_pausa_extensa());
        return tarifaDTO;
    }
    public Optional<Tarifa> obtenerTarifaPorFecha(LocalDate fecha) {
        return tarifaRepository.findTarifaVigenteParaFecha(fecha);
    }

    public Tarifa delete(Tarifa tarifa) {
        tarifaRepository.delete(tarifa);
        return tarifa;
    }

    public Tarifa findById(Long id) {return tarifaRepository.findById(id).orElse(null);}

    public Tarifa update(Tarifa tarifa) {return tarifaRepository.save(tarifa);}


}
