package org.grupo14.msvcusuario.repository;

import org.grupo14.msvcusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findUsuariosByTipoCuenta(String tipoCuenta);
}
