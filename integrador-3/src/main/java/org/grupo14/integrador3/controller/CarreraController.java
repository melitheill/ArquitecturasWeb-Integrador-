package org.grupo14.integrador3.controller;


import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;


    @GetMapping(" ")
    public Iterable<Carrera> findAll() throws Exception {
        return carreraService.findAll();
    }

    @GetMapping("/{idCarerra}")
    public Optional<Carrera> findByIdCarrera(@PathVariable int idCarrera) throws Exception {
         return carreraService.findByIdCarrera(idCarrera);
    }

    @GetMapping("/inscriptos")
    public List<Carrera> obtenerCarrerasConEstudiantesInscriptos(){
        return carreraService.obtenerCarrerasConEstudiantesInscriptos();
    }


    @PostMapping("")
    public Carrera save(@RequestBody Carrera carrera) throws Exception {
        return carreraService.save(carrera);
    }

    @PutMapping({"/{idCarrera}"})
    public Carrera update(@PathVariable int idCarrera, @RequestBody Carrera carrera) throws Exception {
        return carreraService.update(idCarrera, carrera);
    }

    @DeleteMapping("/{idCarrera}")
    public void delete(@PathVariable int idCarrera) throws Exception {
        carreraService.delete(idCarrera);
    }



}
