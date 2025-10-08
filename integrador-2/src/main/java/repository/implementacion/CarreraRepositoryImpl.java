package repository.implementacion;

import DTO.CarerraDTO;
import DTO.CarreraInfoDTO;
import DTO.ReporteDTO;
import entity.Carrera;
import entity.Identificador.Identificador;
import repository.CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager em;
    private static CarreraRepositoryImpl instance = null;

    private CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    public static CarreraRepositoryImpl getInstance(EntityManager em) {
        if (instance == null) {
            instance = new CarreraRepositoryImpl(em);
        }
        return instance;
    }

    @Override
    public void create(Carrera carrera) {
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    @Override
    public Carrera findById(Identificador id) {
        em.getTransaction().begin();
        Carrera carrera = em.find(Carrera.class, id);
        em.getTransaction().commit();
        return carrera;
    }

    @Override
    public void delete(Carrera carrera) {
        em.getTransaction().begin();
        em.remove(carrera);
        em.getTransaction().commit();

    }

    @Override
    public void update(Carrera carrera) {
        em.getTransaction().begin();
        em.merge(carrera);
        em.getTransaction().commit();
    }

    //recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    public List<CarerraDTO> obtenerCarrerasConEstudiantesInscriptos() {
        String query = "SELECT c.carrera, COUNT(c.carrera) FROM Carrera c JOIN EstudianteCarrera ec ON ec.carrera.id = c.id GROUP BY c.carrera ORDER BY COUNT(c.carrera) DESC ";
        Query q = em.createQuery(query);
        List<Object[]> resultados = q.getResultList();
        List<CarerraDTO> carrerasDTO = new ArrayList<>();
        for (Object[] fila : resultados) {
            String nombreCarrera = (String) fila[0];
            Long cantidad = (Long) fila[1]; // COUNT devuelve Long
            carrerasDTO.add(new CarerraDTO(nombreCarrera, cantidad.intValue()));
        }
        return carrerasDTO;
    }

    public List<ReporteDTO> generarReporte() {
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c ORDER BY c.carrera", Carrera.class).getResultList();
        List<ReporteDTO> reportes = new ArrayList<>();

        for(Carrera carrera : carreras){
            ReporteDTO reporte = new ReporteDTO(carrera.getCarrera());

            String inscripcionJQPL = "SELECT ec.inscripcion, COUNT(ec) " +
                    "FROM EstudianteCarrera ec " +
                    "WHERE ec.carrera = :carrera " +
                    "GROUP BY ec.inscripcion " +
                    "ORDER BY ec.inscripcion";

            String egresadosJPQL = "SELECT ec.graduacion, COUNT(ec) " +
                    "FROM EstudianteCarrera ec " +
                    "WHERE ec.carrera = :carrera AND ec.graduacion <> 0" +
                    "GROUP BY ec.graduacion " +
                    "ORDER BY ec.graduacion";

            List<Object[]> inscriptosList = em.createQuery(inscripcionJQPL, Object[].class).setParameter("carrera", carrera).getResultList();
            List<Object[]> esgresadosList = em.createQuery(egresadosJPQL, Object[].class).setParameter("carrera", carrera).getResultList();
            for (Object[] resultado : inscriptosList){
                int anio = (Integer) resultado[0];
                int inscriptos = ((Number) resultado[1]).intValue();
                reporte.getInfoPorAnio().put(anio, new CarreraInfoDTO(inscriptos));
            }
            for (Object[] resultado : esgresadosList){
                int anio = (Integer) resultado[0];
                int egresados = ((Number) resultado[1]).intValue();
                CarreraInfoDTO c = reporte.getInfoPorAnio().get(anio);
                if (c == null){
                    c = new CarreraInfoDTO(0);
                }
                c.setEgresados(egresados);
                reporte.getInfoPorAnio().put(anio, c);
            }
            reportes.add(reporte);
        }
        return reportes;
    }
}
