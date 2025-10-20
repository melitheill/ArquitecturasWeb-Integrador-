package org.grupo14.integrador3.services;

import jakarta.transaction.Transactional;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service("EstudianteService")
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public void delete(int idEstudiante) throws Exception {
        Optional<Estudiante> entity = findById(idEstudiante);
        if(entity.isPresent()){
            estudianteRepository.delete(entity.get());
        } else {
            throw new Exception("El estudiante no existe");
        }
    }

    @Transactional
    public Iterable<Estudiante> findAll(){
        return estudianteRepository.findAll();
    }

    @Transactional
    public Estudiante save(Estudiante estudiante) throws Exception {
        Optional<Estudiante> e = findById(estudiante.getId());
        if(e.isPresent()){
            throw new Exception("El estudiante ya existe");
        }
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public Estudiante update(int id, Estudiante estudiante) throws Exception {
        Optional<Estudiante> est = findById(id);
        if(est.isPresent()){
            return estudianteRepository.save(estudiante);
        } else {
            throw new Exception("El estudiante no existe");
        }
    }

    @Transactional
    public Optional<Estudiante> findById(int idEstudiante){
        return  estudianteRepository.findById(idEstudiante);
    }

    public Iterable<Estudiante> getEstudiantesOrdenados(String orden) {
        String ordenFinal = "apellido";
        String[] criterios = {"id","nombre", "apellido", "edad", "genero", "ciudad", "LU"};
        for(String criterio : criterios) {
            if (criterio.equals(orden.toLowerCase())) {
                ordenFinal = orden.toLowerCase();
            }
        }
        return estudianteRepository.findAll(Sort.by(Sort.Direction.ASC, ordenFinal));
    }

    public Optional<Estudiante> getEstudianteByLU(int lu) {
        return estudianteRepository.findByLU(lu);
    }

    public Iterable<Estudiante> getEstudiantesByGenero(String genero) {
        return estudianteRepository.findByGenero(genero);
    }

    public Iterable<Estudiante> getEstudiantesByCarreraCiudad(String carrera, String ciudad) {
        return estudianteRepository.findByCarreraCiudad(carrera, ciudad);
    }
}
