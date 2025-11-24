package org.grupo14.msvcusuario.repository;

import org.grupo14.msvcusuario.entity.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta,Long> {
}
