package org.grupo14.msvcmonopatin.repository;

import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository  extends JpaRepository<Monopatin,Long> {


    @Query("SELECT m FROM Monopatin m WHERE ABS(m.latitud - ?1) < 0.01 AND ABS(m.longitud - ?2) < 0.01")
    List<Monopatin> findByZona(double lat, double lon);

}
