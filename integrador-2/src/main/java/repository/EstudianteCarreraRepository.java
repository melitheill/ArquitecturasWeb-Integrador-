package repository;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;

public interface EstudianteCarreraRepository extends Repository<EstudianteCarrera> {
    void matricular(Estudiante estudiante, Carrera carrera);
}
