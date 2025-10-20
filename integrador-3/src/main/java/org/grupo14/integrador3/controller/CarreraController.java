package org.grupo14.integrador3.controller;


import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.grupo14.integrador3.dto.CarreraDTO;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;


    @GetMapping("")
    public Iterable<Carrera> findAll() {
        return carreraService.findAll();
    }

    @GetMapping("/{idCarrera}")
    public Optional<Carrera> findById(@PathVariable int idCarrera) {
         return carreraService.findById(idCarrera);
    }

    @GetMapping("/inscriptos")
    public List<CarreraDTO> obtenerCarrerasConEstudiantesInscriptos(){
        return carreraService.obtenerCarrerasConEstudiantesInscriptos();
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Carrera carrera) {
        try {
            return ResponseEntity.ok(carreraService.save(carrera));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @PutMapping({"/{idCarrera}"})
    public ResponseEntity<?> update(@PathVariable int idCarrera, @RequestBody Carrera carrera) {
        try {
            return ResponseEntity.ok(carreraService.update(idCarrera, carrera));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }
    }

    @DeleteMapping("/{idCarrera}")
    public ResponseEntity<?> delete(@PathVariable int idCarrera) {
        try {
            carreraService.delete(idCarrera);
            return ResponseEntity.ok("\"Carrera eliminada\"");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() +"\"}");
        }

    }

    @GetMapping("/reporte")
    public ResponseEntity<?> reporte() {
        return ResponseEntity.ok(carreraService.getReporte());
    }
}
