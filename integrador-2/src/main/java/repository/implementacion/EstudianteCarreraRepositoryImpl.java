package repository.implementacion;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import entity.Identificador.Identificador;
import repository.EstudianteCarreraRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {
    private EntityManager em;
    private static EstudianteCarreraRepository instance = null;

    private EstudianteCarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    public static EstudianteCarreraRepository getInstance(EntityManager em){
        if(instance == null){
            instance = new EstudianteCarreraRepositoryImpl(em);
        }
        return instance;
    }


    @Override
    public void create(EstudianteCarrera ec) {
        em.getTransaction().begin();
        try{
            em.persist(ec);
        } catch (Exception ex) {
            Estudiante e = ec.getEstudiante();
            Carrera c = ec.getCarrera();
            String salida = "El estudiante dni " + e.getDNI() + " se encuentra matriculado en la carrera " + c.getCarrera();
            System.out.println(salida);
        }
        em.getTransaction().commit();
    }

    @Override
    public EstudianteCarrera findById(Identificador id) {
        em.getTransaction().begin();
        EstudianteCarrera ec = em.find(EstudianteCarrera.class, id);
        em.getTransaction().commit();
        return ec;
    }

    @Override
    public void delete(EstudianteCarrera ec) {
        em.getTransaction().begin();
        em.remove(ec);
        em.getTransaction().commit();
    }

    @Override
    public void update(EstudianteCarrera ec) {
        em.getTransaction().begin();
        em.merge(ec);
        em.getTransaction().commit();
    }
    public void matricular(Estudiante estudiante, Carrera carrera){
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera, 2025,0,0);
        em.getTransaction().begin();
        em.persist(estudianteCarrera);
        em.getTransaction().commit();
    }
}
