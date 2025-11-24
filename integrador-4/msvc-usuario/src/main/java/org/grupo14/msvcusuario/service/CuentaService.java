package org.grupo14.msvcusuario.service;

import lombok.RequiredArgsConstructor;
import org.grupo14.msvcusuario.entity.Cuenta;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public Cuenta save(Cuenta usuario) {
        return cuentaRepository.save(usuario);
    }

    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta update(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta delete(Cuenta cuenta) {
        cuentaRepository.delete(cuenta);
        return cuenta;
    }

    public Cuenta anularCuenta(Cuenta cuenta) {
        cuenta.setAnulada(true);
        cuentaRepository.save(cuenta);
        return cuenta;
    }
}
