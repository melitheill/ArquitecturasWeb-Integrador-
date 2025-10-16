package org.grupo14.integrador3.controller;

import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;
import org.grupo14.integrador3.services.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matricula")
public class EstudianteCarreraController {
    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @GetMapping("")
    public Iterable<EstudianteCarrera> findAll(){
        return estudianteCarreraService.findAll();
    }

    @GetMapping("/{idEstudiante}/{idCarrera}")
    public Optional<EstudianteCarrera> findById(@PathVariable int idEstudiante, @PathVariable int idCarrera){
        return estudianteCarreraService.findById(idEstudiante, idCarrera);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody EstudianteCarrera matricula){
        try {
            return ResponseEntity.ok(estudianteCarreraService.save(matricula));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @PutMapping("/{idEstudiante}/{idCarrera}")
    public ResponseEntity<?> update(@PathVariable int idEstudiante, @PathVariable int idCarrera, @RequestBody EstudianteCarrera matricula){
        try {
            return ResponseEntity.ok(estudianteCarreraService.update(idEstudiante, idCarrera, matricula));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @DeleteMapping("/{idEstudiante}/{idCarrera}")
    public ResponseEntity<?> delete(@PathVariable int idEstudiante, @PathVariable int idCarrera){
        try {
            estudianteCarreraService.delete(idEstudiante, idCarrera);
            return ResponseEntity.ok("Se elimino la matricula del estudiante");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

}
