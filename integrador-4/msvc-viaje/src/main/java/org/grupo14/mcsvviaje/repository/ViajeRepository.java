package org.grupo14.mcsvviaje.repository;

import feign.Param;

import org.grupo14.mcsvviaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT v FROM Viaje v WHERE v.monopatin = :idMonopatin")
    List<Viaje>  obtenerViajesPorMonopatin(Long idMonopatin);

    @Query("SELECT v FROM Viaje v " +
            "where v.usuario = :idUsuario " +
            "AND YEAR(v.fechaHoraFin) BETWEEN :yearInicio AND :yearFin " +
            "AND MONTH(v.fechaHoraFin) BETWEEN :mesInicio AND :mesFin")
    List<Viaje> obtenerViajesPorUsuario(Long idUsuario, int yearInicio,  int mesInicio, int yearFin,int mesFin);



    @Query("SELECT v.monopatin, COUNT(v) " +
            "FROM Viaje v " +
            "WHERE YEAR(v.fechaHoraInicio) = :anio " +
            "GROUP BY v.monopatin " +
            "HAVING COUNT(v) >= :cantidad")
    List<Object[]> getCantidadViajesPorMonopatinMayor(@Param("anio") int anio,int cantidad);
}
