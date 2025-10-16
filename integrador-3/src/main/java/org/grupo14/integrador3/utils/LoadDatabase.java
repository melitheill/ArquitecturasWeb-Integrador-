package org.grupo14.integrador3.utils;

import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.repository.CarreraRepository;
import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.grupo14.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoadDatabase {

    private final EstudianteCarreraRepository estudianteCarreraRepository;
    private final CarreraRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final CSVReader csvReader;

    @Autowired
    public LoadDatabase(EstudianteCarreraRepository estudianteCarreraRepository, CarreraRepository carreraRepository, EstudianteRepository estudianteRepository, CSVReader csvReader){
        this.estudianteCarreraRepository = estudianteCarreraRepository;
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.csvReader = csvReader;
    }

    public void cargarDatos() throws IOException {
        csvReader.cargarDatos();
    }
}
