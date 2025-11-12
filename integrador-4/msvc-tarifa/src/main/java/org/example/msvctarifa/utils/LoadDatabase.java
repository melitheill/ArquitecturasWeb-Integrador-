package org.example.msvctarifa.utils;

import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.service.TarifaService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadDatabase {
    private final TarifaService tarifaService;
    public LoadDatabase(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }

    public void cargarDatos() {
        tarifaService.save(new Tarifa(80, 100, LocalDate.now()));
    }
}
