package org.example.msvcparada.controller;

import lombok.RequiredArgsConstructor;
import org.example.msvcparada.DTO.ParadaDTO;
import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parada")
@RequiredArgsConstructor
public class ParadaController {


    private final ParadaService paradaService;

    @GetMapping("")
    public ResponseEntity<List<ParadaDTO>> getAll(){
        List<Parada> paradas = paradaService.getAll();
        if (paradas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paradaService.convertirDTO(paradas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaDTO> getById(@PathVariable Long id){
        Parada parada = paradaService.findById(id);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paradaService.convertirDTO(parada));
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
