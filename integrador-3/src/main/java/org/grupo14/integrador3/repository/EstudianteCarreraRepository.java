package org.grupo14.integrador3.repository;

import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, EstudianteCarreraID> {
}
