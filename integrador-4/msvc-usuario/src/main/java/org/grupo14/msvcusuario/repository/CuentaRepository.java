package org.grupo14.msvcusuario.repository;

import org.grupo14.msvcusuario.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
}
