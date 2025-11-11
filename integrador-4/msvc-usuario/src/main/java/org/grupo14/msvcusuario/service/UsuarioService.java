package org.grupo14.msvcusuario.service;

import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario update(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }

    public Usuario delete(Usuario usuario) {
          usuarioRepository.delete(usuario);
          return usuario;
    }
}
