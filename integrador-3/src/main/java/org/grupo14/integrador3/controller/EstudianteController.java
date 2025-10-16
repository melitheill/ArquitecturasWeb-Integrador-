package org.grupo14.integrador3.controller;


import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;


    @GetMapping("")
    public Iterable<Estudiante> findAll() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estudiante> findById(@PathVariable int id) {
        return estudianteService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.save(estudiante));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.ok(estudianteService.update(id, estudiante));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            estudianteService.delete(id);
            return ResponseEntity.ok("\"Estudiante eliminado\"");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }
}
