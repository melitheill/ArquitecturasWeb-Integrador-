package org.grupo14.msvcmonopatin.repository;

import feign.Param;
import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository  extends JpaRepository<Monopatin,Long> {


    @Query("""
    SELECT m FROM Monopatin m
    WHERE m.estado = 'disponible'
    AND (6371 * acos(
        cos(radians(:lat)) * cos(radians(m.latitud)) *
        cos(radians(m.longitud) - radians(:lon)) +
        sin(radians(:lat)) * sin(radians(m.latitud))
    )) <= 5
    """)
    List<Monopatin> findByZona(@Param("lat") double lat,@Param("lon") double lon);

    //Solo se devuelven los monopatines que están dentro del radio de 5 km desde el punto de referencia.
}
