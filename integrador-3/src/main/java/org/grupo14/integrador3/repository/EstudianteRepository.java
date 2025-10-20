package org.grupo14.integrador3.repository;

import org.grupo14.integrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {

    @Query("SELECT e FROM Estudiante e WHERE e.LU = :lu")
    Optional<Estudiante> findByLU(int lu);

    @Query("SELECT e FROM Estudiante e WHERE LOWER(e.genero) = LOWER(:genero)")
    Iterable<Estudiante> findByGenero(String genero);

    @Query("SELECT e FROM Estudiante e JOIN EstudianteCarrera ec ON ec.id.estudiante = e WHERE LOWER(ec.id.carrera.carrera) = LOWER(:carrera) AND e.ciudad = :ciudad")
    Iterable<Estudiante> findByCarreraCiudad(String carrera, String ciudad);
}
