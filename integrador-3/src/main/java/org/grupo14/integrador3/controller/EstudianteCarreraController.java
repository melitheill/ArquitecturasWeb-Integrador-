package org.grupo14.integrador3.controller;

import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.services.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class EstudianteCarreraController {
    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @GetMapping("")
    public Iterable<EstudianteCarrera> findAll(){
        return estudianteCarreraService.findAll();
    }


}
