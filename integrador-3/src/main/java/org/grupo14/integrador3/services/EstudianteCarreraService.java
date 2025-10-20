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
    public void delete(int idEstudiante, int idCarrera) throws Exception{
        EstudianteCarreraID ecID = convertirId(idEstudiante, idCarrera);
        System.out.println(ecID);
        if(ecID != null && findById(ecID).isPresent()){
            EstudianteCarrera ec = estudianteCarreraRepository.findById(ecID).get();
            estudianteCarreraRepository.delete(ec);
        } else {
            throw new Exception("El estudiante no esta matriculado en esa carrera");
        }
    }

    @Transactional
    public Iterable<EstudianteCarrera> findAll(){
        return estudianteCarreraRepository.findAll();
    }

    public Optional<EstudianteCarrera> findById(EstudianteCarreraID id){
        return estudianteCarreraRepository.findById(id);
    }

    @Transactional
    public Optional<EstudianteCarrera> findById(int idEstudiante, int idCarrera){
        EstudianteCarreraID ecID = convertirId(idEstudiante,idCarrera);
        if(ecID != null){
            return findById(ecID);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public EstudianteCarrera save(EstudianteCarrera ec) throws Exception {
        Optional<EstudianteCarrera> entity = findById(ec.getId());
        if(entity.isPresent()){
            throw new Exception("El estudiante ya esta matriculado en esa carrera");
        }
        return estudianteCarreraRepository.save(ec);
    }

    @Transactional
    public EstudianteCarrera update(int idEstudiante, int idCarrera, EstudianteCarrera ec) throws Exception{
        EstudianteCarreraID id = convertirId(idEstudiante, idCarrera);
        if(id != null && findById(id).isPresent()){
            return estudianteCarreraRepository.save(ec);
        } else {
            throw new Exception("El estudiante no esta matriculado en esa carrera");
        }
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

    public EstudianteCarrera matricular(EstudianteCarreraID estudianteCarreraID) throws Exception{
        if(estudianteCarreraRepository.findById(estudianteCarreraID).isPresent()){
            throw new Exception("El estudiante ya esta matriculado en esa carrera");
        }
        EstudianteCarrera matricula = new EstudianteCarrera(estudianteCarreraID.getEstudiante(), estudianteCarreraID.getCarrera(), 2025, 0, 0);
        return estudianteCarreraRepository.save(matricula);
    }
}
