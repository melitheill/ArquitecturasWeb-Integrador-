package org.example.msvctarifa.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.DTO.TarifaDTO;
import org.example.msvctarifa.entity.Tarifa;
import org.example.msvctarifa.service.TarifaService;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifa")
@RequiredArgsConstructor
public class TarifaController {


    private final TarifaService tarifaService;

    @GetMapping("")
    public ResponseEntity<List<Tarifa>> getAll(){
        List<Tarifa> tarifa = tarifaService.getAll();
        if (tarifa.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getById(@PathVariable Long id){
        Tarifa tarifa = tarifaService.findById(id);
        if (tarifa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarifa);
    }

//    @PostMapping("")
//    public ResponseEntity<Tarifa> create(@RequestBody Tarifa tarifa){
//        return ResponseEntity.ok(tarifaService.save(tarifa));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarifa> update(@PathVariable Long id, @RequestBody Tarifa tarifa){
        Tarifa v = tarifaService.findById(id);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarifaService.update(tarifa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarifa> delete(@PathVariable Long id){
        Tarifa tarifa = tarifaService.findById(id);
        if (tarifa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tarifaService.delete(tarifa));
        }
    }
    @PostMapping
    public ResponseEntity<Tarifa> crearTarifa(@RequestBody Tarifa nueva) {
        Tarifa creada = tarifaService.save(nueva);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/activa")
    public ResponseEntity<TarifaDTO> getTarifaActiva() {
        TarifaDTO tarifa = tarifaService.obtenerTarifaActiva();
        return ResponseEntity.ok(tarifa);
    }
    @GetMapping("/vigente")
    public ResponseEntity<Tarifa> getTarifaPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return tarifaService.obtenerTarifaPorFecha(fecha)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
