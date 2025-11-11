package org.grupo14.mcsvviaje.controller;

import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping("")
    public ResponseEntity<List<Viaje>> getAll(){
        List<Viaje> viajes = viajeService.getAll();
        if (viajes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(viajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getById(@PathVariable Long id){
        Viaje viaje = viajeService.findById(id);
        if (viaje == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viaje);
    }

    @PostMapping("")
    public ResponseEntity<Viaje> create(@RequestBody Viaje viaje){
        return ResponseEntity.ok(viajeService.save(viaje));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> update(@PathVariable Long id, @RequestBody Viaje viaje){
        Viaje v = viajeService.findById(id);
        if (v == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viajeService.update(viaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> delete(@PathVariable Long id){
        Viaje viaje = viajeService.findById(id);
        if (viaje == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(viajeService.delete(viaje));
        }
    }
}
