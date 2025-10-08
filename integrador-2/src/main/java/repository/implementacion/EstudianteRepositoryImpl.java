package repository.implementacion;

import DTO.EstudianteDTO;
import entity.Carrera;
import entity.Estudiante;
import entity.Identificador.Identificador;
import repository.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private EntityManager em;
    private static EstudianteRepository instance = null;

    private EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public static EstudianteRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new EstudianteRepositoryImpl(em);
        }
        return instance;
    }

    @Override
    public Estudiante findById(Identificador id) {
        em.getTransaction().begin();
        Estudiante estudiante = em.find(Estudiante.class, id);
        em.getTransaction().commit();
        return estudiante;
    }

    @Override
    public void create(Estudiante estudiante) {
       em.getTransaction().begin();
       em.persist(estudiante);
       em.getTransaction().commit();
    }

    @Override
    public void delete(Estudiante estudiante) {
       em.getTransaction().begin();
       em.remove(estudiante);
       em.getTransaction().commit();
    }

    @Override
    public void update(Estudiante estudiante) {
       em.getTransaction().begin();
       em.merge(estudiante);
       em.getTransaction().commit();
    }


    @Override
    public List<EstudianteDTO> obtenerEstudiantesOrdenados(String orden) {
        em.getTransaction().begin();
        String query = "SELECT e FROM Estudiante e ORDER BY e."+orden;
        TypedQuery<Estudiante> q = em.createQuery(query,  Estudiante.class);
        return convertirDTO(q.getResultList());
    }

    @Override
    public List<EstudianteDTO> obtenerEstudiantesPorGenero(String genero){
        String query = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        //TypedQuery<Estudiante> → evita tener que hacer cast al tipo de resultado.
        TypedQuery<Estudiante> q = em.createQuery(query, Estudiante.class);
        q.setParameter("genero", genero);
        return convertirDTO(q.getResultList());
    }

    public List<EstudianteDTO> obtenerEstudiantesPorCarreraCiudad(Carrera carrera, String ciudad){
        String query = "SELECT e FROM Estudiante e JOIN EstudianteCarrera ec ON ec.estudiante.DNI = e.DNI WHERE ec.carrera = :carrera AND e.ciudad = :ciudad";
        TypedQuery<Estudiante> q = em.createQuery(query, Estudiante.class);
        q.setParameter("carrera", carrera);
        q.setParameter("ciudad", ciudad);
        return convertirDTO(q.getResultList());
    }

    private List<EstudianteDTO> convertirDTO(List<Estudiante> estudiantes){
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for(Estudiante estudiante : estudiantes){
            estudiantesDTO.add(new EstudianteDTO(estudiante.getNombre(), estudiante.getApellido()));
        }
        return estudiantesDTO;
    }


    public EstudianteDTO findByLU(int LU){
        String jpql = "SELECT e FROM Estudiante e WHERE LU = :LU";
        Estudiante e = em.createQuery(jpql, Estudiante.class).setParameter("LU", LU).getSingleResult();
        EstudianteDTO dto = new EstudianteDTO(e.getNombre(), e.getApellido());
        return  dto;
    }

    @Override
    public void darDeAlta(Estudiante estudiante) {
        create(estudiante);
    }
}
