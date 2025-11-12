package org.grupo14.mcsvviaje.repository;

import org.grupo14.mcsvviaje.DTO.ViajeUsuarioDTO;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT v FROM Viaje v WHERE v.monopatin = :idMonopatin")
    List<Viaje>  obtenerViajesPorMonopatin(Long idMonopatin);
    @Query("SELECT v FROM Viaje v where v.usuario = :idUsuario")
    List<Viaje> obtenerViajesPorUsuario(Long idUsuario);
}
