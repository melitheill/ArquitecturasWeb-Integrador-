package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("EstudianteService")
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public void delete(int idEstudiante){
        Optional<Estudiante> entity = estudianteRepository.findById(idEstudiante);
        if(entity.isPresent()){
            estudianteRepository.delete(entity.get());
        }
    }

    @Transactional
    public Iterable<Estudiante> findAll(){
        return estudianteRepository.findAll();
    }

    @Transactional
    public Estudiante save(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }
    @Transactional
    public Estudiante update(int id, Estudiante estudiante){
        Optional<Estudiante> est = estudianteRepository.findById(id);
        if(est.isPresent()){
            return estudianteRepository.save(estudiante);
        }
        return null;
    }

    @Transactional
    public Optional<Estudiante> findById(int idEstudiante){
        return  estudianteRepository.findById(idEstudiante);
    }
}
