package org.example.msvcparada.controller;

import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parada")
public class ParadaController {

    @Autowired
    ParadaService paradaService;

    @GetMapping("")
    public ResponseEntity<List<Parada>> getAll(){
        List<Parada> parada = paradaService.getAll();
        if (parada.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(parada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> getById(@PathVariable Long id){
        Parada parada = paradaService.findById(id);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parada);
    }

    @PostMapping("")
    public ResponseEntity<Parada> create(@RequestBody Parada parada){
        return ResponseEntity.ok(paradaService.save(parada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parada> update(@PathVariable Long id, @RequestBody Parada tarifa){
        Parada parada = paradaService.findById(id);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paradaService.update(tarifa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Parada> delete(@PathVariable Long id){
        Parada parada = paradaService.findById(id);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(paradaService.delete(parada));
        }
    }
}
