package org.grupo14.msvcusuario.service;

import org.grupo14.msvcusuario.dto.UsoMonopatinesDTO;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.feignClients.MonopatinFeignClient;
import org.grupo14.msvcusuario.feignClients.ViajeFeignClient;
import org.grupo14.msvcusuario.model.Viaje;
import org.grupo14.msvcusuario.model.Monopatin;
import org.grupo14.msvcusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MonopatinFeignClient monopatinFeignClient;

    @Autowired
    private ViajeFeignClient viajeFeignClient;

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

    public UsoMonopatinesDTO getUsoMonopatines(Long idUsuario) {
        List<Viaje> viajes = viajeFeignClient.getViajesUsuario(idUsuario);
        int kmRecorridos = 0;
        for (Viaje viaje : viajes) {
            kmRecorridos = kmRecorridos + viaje.getKmRecorridos();
        }
        return new UsoMonopatinesDTO(kmRecorridos);
    }

    public List<Monopatin> getMonopatinMasCercano(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + usuarioId)
        );

        List<Monopatin> lista = monopatinFeignClient.getByZona(usuario.getLatitud(), usuario.getLongitud());
        return (lista != null) ? lista : List.of(); // nunca null
    }
}
