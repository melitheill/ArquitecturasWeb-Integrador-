package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
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
    @Autowired
    private CarreraService carreraService;
    @Autowired
    private EstudianteService estudianteService;

    @Transactional
    public void delete(EstudianteCarreraID ecID ){
        Optional<EstudianteCarrera> ec = estudianteCarreraRepository.findById(ecID);
        EstudianteCarrera ecFinal = ec.get();
        estudianteCarreraRepository.delete(ecFinal);
    }

    public void delete(int idEstudiante, int idCarrera){
        EstudianteCarreraID ecID = convertirId(idEstudiante, idCarrera);
        delete(ecID);
    }

    @Transactional
    public Iterable<EstudianteCarrera> findAll(){
        return estudianteCarreraRepository.findAll();
    }

    @Transactional
    public Optional<EstudianteCarrera> findById(EstudianteCarreraID id){
        return estudianteCarreraRepository.findById(id);
    }

    public Optional<EstudianteCarrera> findById(int idEstudiante, int idCarrera){
        EstudianteCarreraID ecID = convertirId(idEstudiante,idCarrera);
        return estudianteCarreraRepository.findById(ecID);
    }

    @Transactional
    public EstudianteCarrera save(EstudianteCarrera ec){
        return estudianteCarreraRepository.save(ec);
    }

//    @Transactional
//    public EstudianteCarrera update(EstudianteCarreraID id, EstudianteCarrera ec){
//        Optional<EstudianteCarrera> entity = estudianteCarreraRepository.findById(id);
//        if(entity.isPresent()){
//            EstudianteCarrera estc = entity.get();
//            estc = estudianteCarreraRepository.save(estc);
//            return estc;
//        } else {
//            return estudianteCarreraRepository.save(ec);
//
//        }
//    }
    @Transactional
    public EstudianteCarrera update(int idEstudiante, int idCarrera, EstudianteCarrera ec){
        EstudianteCarreraID id = convertirId(idEstudiante, idCarrera);
        if(id != null){
            Optional<EstudianteCarrera> entity = estudianteCarreraRepository.findById(id);
            if(entity.isPresent()){
                return estudianteCarreraRepository.save(ec);
            }
        }
        return null;
    }

    public EstudianteCarreraID convertirId(int idEstudiante, int idCarrera){
        Optional<Carrera> c = carreraService.findById(idCarrera);
        Optional<Estudiante> e = estudianteService.findById(idEstudiante);
        if(c.isPresent() && e.isPresent()) {
            Carrera carrera = c.get();
            Estudiante estudiante = e.get();
            return new EstudianteCarreraID(estudiante, carrera);
        } else {
            return null;
        }
    }
}
