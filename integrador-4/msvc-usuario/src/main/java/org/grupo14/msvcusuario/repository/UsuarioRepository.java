package org.grupo14.msvcusuario.repository;


import org.grupo14.msvcusuario.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Number> {

    List<Usuario> findUsuariosByTipoCuenta(String tipoCuenta);
}
