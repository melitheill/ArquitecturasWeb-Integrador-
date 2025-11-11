package org.grupo14.mcsvviaje.repository;

import org.grupo14.mcsvviaje.entity.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepository extends JpaRepository<Tiempo, Long> {
}
