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
    public Iterable<Carrera> findAll(){
        return carreraRepository.findAll();
    }

    @Transactional
    public Optional<Carrera> findById(Integer idCarrera){
        return carreraRepository.findById(idCarrera);
    }

    @Transactional
    public List<Carrera> obtenerCarrerasConEstudiantesInscriptos(){
        return carreraRepository.obtenerCarrerasConEstudiantesInscriptos();
    }

    @Transactional
    public Carrera save(Carrera carrera) throws Exception {
        Optional<Carrera> c = findById(carrera.getId());
        if (c.isPresent()) {
            throw new Exception("La carrera ya existe");
        }
        return carreraRepository.save(carrera);
    }

    @Transactional
    public Carrera update(int idCarrera,Carrera carrera) throws Exception {
        Optional<Carrera> entity = findById(idCarrera);
        if(entity.isPresent()){
            return carreraRepository.save(carrera);
        }else {
            throw new Exception("La carrera no existe");
        }
    }

    @Transactional
    public void delete(int idCarrera) throws Exception {
        Optional<Carrera> entity = findById(idCarrera);
        if(entity.isPresent()){
            carreraRepository.delete(entity.get());
        } else {
            throw new Exception("La carrera no existe");
        }
    }

}
