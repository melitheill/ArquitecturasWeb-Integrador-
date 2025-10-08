package repository;

import repository.implementacion.CarreraRepositoryImpl;
import repository.implementacion.EstudianteCarreraRepositoryImpl;
import repository.implementacion.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;

public class RepositoryFactory {
    public CarreraRepository getCarreraRepository(EntityManager em) {
        return CarreraRepositoryImpl.getInstance(em);
    }
    public EstudianteRepository getEstudianteRepository(EntityManager em) {
        return EstudianteRepositoryImpl.getInstance(em);
    }
    public EstudianteCarreraRepository getEstudianteCarreraRepository(EntityManager em) {
        return EstudianteCarreraRepositoryImpl.getInstance(em);
    }
}
