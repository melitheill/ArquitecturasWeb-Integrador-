package org.grupo14.msvcusuario.service;

import org.grupo14.msvcusuario.dto.UsoMonopatinesDTO;
import org.grupo14.msvcusuario.entity.Cuenta;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Long, UsoMonopatinesDTO> getUsoMonopatines(Long idUsuario, int year, int mesInicio, int mesFin, boolean otrosUsuarios) {
        Map<Long, UsoMonopatinesDTO> result = new HashMap<>();
        UsoMonopatinesDTO usoMonopatinesDTO = getUsoMonopatinesDTO(idUsuario, year, mesInicio, mesFin);
        result.put(idUsuario, usoMonopatinesDTO);
        if (otrosUsuarios) {
            Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
            if (usuario != null) {
                //devuelve el uso de los usuarios de todas las cuentas asociadas
                List<Cuenta> cuentas = usuario.getCuentas();
                for (Cuenta cuenta : cuentas) {
                    List<Usuario> usuarios = cuenta.getUsuario();
                    for (Usuario u : usuarios) {
                        result.put(u.getId(), getUsoMonopatinesDTO(u.getId(), year, mesInicio, mesFin));
                    }
                }
            }
        }
        return result;
    }

    private UsoMonopatinesDTO getUsoMonopatinesDTO(Long idUsuario, int year, int mesInicio, int mesFin) {
        List<Viaje> viajes = viajeFeignClient.getViajesUsuario(idUsuario, year, mesInicio, mesFin);
        int kmRecorridos = 0;
        int tiempoUsoSinPausa = 0;
        int tiempoUsoConPausa = 0;
        for (Viaje viaje : viajes) {
            kmRecorridos = kmRecorridos + viaje.getKmRecorridos();
            tiempoUsoSinPausa += viaje.getTiempoUsoSinPausa();
            tiempoUsoConPausa = tiempoUsoConPausa + viaje.getTiempoUsoConPausa();
        }
        UsoMonopatinesDTO usoMonopatinesDTO = new UsoMonopatinesDTO(kmRecorridos, viajes.size(), tiempoUsoSinPausa, tiempoUsoConPausa);
        return usoMonopatinesDTO;
    }

    public List<Monopatin> getMonopatinMasCercano(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + usuarioId)
        );

        List<Monopatin> lista = monopatinFeignClient.getByZona(usuario.getLatitud(), usuario.getLongitud());
        return (lista != null) ? lista : List.of(); // nunca null
    }
}
