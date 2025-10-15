package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;
import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("EstudianteCarreraService")
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    @Transactional
    public void delete(EstudianteCarrera ec){
        estudianteCarreraRepository.delete(ec);
    }

    @Transactional
    public Iterable<EstudianteCarrera> findAll(){
        return estudianteCarreraRepository.findAll();
    }

    @Transactional
    public Optional<EstudianteCarrera> findById(EstudianteCarreraID id){
        return estudianteCarreraRepository.findById(id);
    }

    @Transactional
    public EstudianteCarrera save(EstudianteCarrera ec){
        return estudianteCarreraRepository.save(ec);
    }

    @Transactional
    public EstudianteCarrera update(EstudianteCarrera ec){
        return estudianteCarreraRepository.save(ec);
    }
}
