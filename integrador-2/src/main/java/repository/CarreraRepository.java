package repository;

import DTO.ReporteDTO;
import entity.Carrera;
import DTO.CarerraDTO;
import java.util.List;

public interface CarreraRepository extends Repository<Carrera> {
    List<CarerraDTO> obtenerCarrerasConEstudiantesInscriptos();
    List<ReporteDTO> generarReporte();
}
