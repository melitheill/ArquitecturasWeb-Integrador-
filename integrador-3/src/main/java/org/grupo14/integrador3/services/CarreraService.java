package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CarreraService")
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;


    @Transactional
    public Iterable<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    @Transactional
    public Optional<Carrera> findByIdCarrera(int idCarrera){
        return carreraRepository.findById(idCarrera);
    }

    @Transactional
    public List<Carrera> obtenerCarrerasConEstudiantesInscriptos(){
        return carreraRepository.obtenerCarrerasConEstudiantesInscriptos();
    }

    @Transactional
    public Carrera save(Carrera carrera) throws Exception {
        return carreraRepository.save(carrera);
    }

    @Transactional
    public Carrera update(int idCarrera,Carrera carrera) throws Exception {
        Optional<Carrera> entity = carreraRepository.findById(idCarrera);
        if(entity.isPresent()){
            Carrera c = entity.get();
            carreraRepository.save(c);
            return c;
        }else {
            return carrera;
        }
    }

    @Transactional
    public void delete(int idCarrera) throws Exception {
        Optional<Carrera> entity = carreraRepository.findById(idCarrera);
        if(entity.isPresent()){
            carreraRepository.delete(entity.get());
        }
    }

}
