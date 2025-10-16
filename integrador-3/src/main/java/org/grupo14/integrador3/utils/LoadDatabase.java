package org.grupo14.integrador3.utils;

import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.repository.CarreraRepository;
import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.grupo14.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {

    private final EstudianteCarreraRepository estudianteCarreraRepository;
    private final CarreraRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;

    @Autowired
    public LoadDatabase(EstudianteCarreraRepository estudianteCarreraRepository, CarreraRepository carreraRepository, EstudianteRepository estudianteRepository){
        this.estudianteCarreraRepository = estudianteCarreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    public void cargarDatos(){
        Estudiante e = new Estudiante(1, "a", "a", 1, "a", "a", 1);
        estudianteRepository.save(e);
        Carrera c = new Carrera(1, "@", 2);
        carreraRepository.save(c);
    }
}
