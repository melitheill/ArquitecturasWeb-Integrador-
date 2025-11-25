package org.grupo14.msvcfactura.repository;

import feign.Param;
import org.grupo14.msvcfactura.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("SELECT SUM(f.valor) FROM Factura f " +
            "WHERE YEAR(f.fecha) = :anio " +
            "AND MONTH(f.fecha) BETWEEN :mesInicio AND :mesFin")
    int getTotalFacturadoEntre(@Param("anio") int anio,
                               @Param("mesInicio") int mesInicio,
                               @Param("mesFin") int mesFin);

}
