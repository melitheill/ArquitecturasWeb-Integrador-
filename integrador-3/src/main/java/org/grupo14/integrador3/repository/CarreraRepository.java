package org.grupo14.integrador3.repository;

import org.grupo14.integrador3.dto.CarreraDTO;
import org.grupo14.integrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {


  //recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
    @Query("SELECT c.carrera, COUNT(c.carrera) FROM Carrera c JOIN EstudianteCarrera ec " +
            "ON ec.id.carrera = c GROUP BY c.carrera ORDER BY COUNT(c.carrera) DESC")
    List<CarreraDTO> obtenerCarrerasConEstudiantesInscriptos();


}
