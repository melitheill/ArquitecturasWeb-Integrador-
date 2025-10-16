package org.grupo14.integrador3.controller;

import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;
import org.grupo14.integrador3.services.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
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
    public EstudianteCarrera save(@RequestBody EstudianteCarrera carrera){
        return estudianteCarreraService.save(carrera);
    }

    @PutMapping("/{idEstudiante}/{idCarrera}")
    public EstudianteCarrera update(@PathVariable int idEstudiante, @PathVariable int idCarrera, @RequestBody EstudianteCarrera carrera){
        return estudianteCarreraService.update(idEstudiante, idCarrera, carrera);
    }

    @DeleteMapping("/{idEstudiante}/{idCarrera}")
    public void delete(@PathVariable int idEstudiante, @PathVariable int idCarrera){
        estudianteCarreraService.delete(idEstudiante, idCarrera);
    }

}
