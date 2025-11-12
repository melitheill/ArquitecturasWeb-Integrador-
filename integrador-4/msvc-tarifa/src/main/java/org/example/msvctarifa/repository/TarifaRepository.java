package org.example.msvctarifa.repository;

import org.example.msvctarifa.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    Optional<Tarifa> findByActivaTrue();

    @Query("SELECT t FROM Tarifa t WHERE t.fechaVigencia <= :fecha ORDER BY t.fechaVigencia DESC LIMIT 1")
    Optional<Tarifa> findTarifaVigenteParaFecha(@Param("fecha") LocalDate fecha);
}