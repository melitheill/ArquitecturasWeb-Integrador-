package repository;

import DTO.EstudianteDTO;
import entity.Carrera;
import entity.Estudiante;

import java.util.List;

public interface EstudianteRepository extends Repository<Estudiante> {
    void darDeAlta(Estudiante estudiante);
    List<EstudianteDTO> obtenerEstudiantesOrdenados(String orden);
    EstudianteDTO findByLU(int lu);
    List<EstudianteDTO> obtenerEstudiantesPorGenero(String genero);
    List<EstudianteDTO> obtenerEstudiantesPorCarreraCiudad(Carrera carrera, String ciudad);
}
