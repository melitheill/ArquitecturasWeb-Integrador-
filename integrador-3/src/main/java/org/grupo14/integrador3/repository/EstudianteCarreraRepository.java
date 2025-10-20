package org.grupo14.integrador3.repository;

import org.grupo14.integrador3.dto.CantidadPorAnioDTO;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraID> {

    @Query("SELECT ec.inscripcion, COUNT(ec) FROM EstudianteCarrera ec WHERE ec.id.carrera = :carrera GROUP BY ec.inscripcion ORDER BY ec.inscripcion")
    List<CantidadPorAnioDTO> getCarreraByInscripcion(Carrera carrera);

    @Query("SELECT ec.graduacion, COUNT(ec) FROM EstudianteCarrera ec WHERE ec.id.carrera = :carrera and ec.graduacion <> 0 GROUP BY ec.graduacion ORDER BY ec.graduacion")
    List<CantidadPorAnioDTO> getCarreraByGraduacion(Carrera carrera);
}
