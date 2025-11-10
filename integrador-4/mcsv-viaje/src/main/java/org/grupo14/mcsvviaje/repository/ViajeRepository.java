package org.grupo14.mcsvviaje.repository;

import org.grupo14.mcsvviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

}
