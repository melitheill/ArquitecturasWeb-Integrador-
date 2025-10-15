package org.grupo14.integrador3.utils;

import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {

    private final EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    public LoadDatabase(EstudianteCarreraRepository estudianteCarreraRepository){
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    public void cargarDatos(){
        estudianteCarreraRepository.deleteAll();
    }
}
