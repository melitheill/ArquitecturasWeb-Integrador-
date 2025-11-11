package org.grupo14.msvcfactura.repository;

import org.grupo14.msvcfactura.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
