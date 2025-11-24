package org.grupo14.msvcusuario.service;

import lombok.RequiredArgsConstructor;
import org.grupo14.msvcusuario.dto.CuentaDTO;
import org.grupo14.msvcusuario.dto.PagoResponseDTO;
import org.grupo14.msvcusuario.dto.PagoRequestDTO;
import org.grupo14.msvcusuario.entity.Cuenta;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.feignClients.PagoMockFeignClient;
import org.grupo14.msvcusuario.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final PagoMockFeignClient pagoMockFeignClient;

    public List<CuentaDTO> convertirDTO(List<Cuenta> cuentas){
        List<CuentaDTO> cuentaDTO = new ArrayList<>();
        for(Cuenta cuenta : cuentas){
            cuentaDTO.add(convertirDTO(cuenta));
        }
        return cuentaDTO;
    }
    public CuentaDTO convertirDTO(Cuenta cuenta){
        return  new CuentaDTO(cuenta.getNroCuenta(), cuenta.getSaldo(), cuenta.isAnulada());
    }

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

    public Cuenta cargarSaldo(Long nroCuenta, Double monto) {
        Cuenta cuenta = cuentaRepository.findById(nroCuenta).orElseThrow();

        // llamás al mock de MP
        PagoRequestDTO request = new PagoRequestDTO(nroCuenta, monto);
        PagoResponseDTO response = pagoMockFeignClient.cargarSaldo(request);

        if ("APROBADO".equalsIgnoreCase(response.getEstado())) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            cuentaRepository.save(cuenta);
            return cuenta ;
        } else {
            // si querés, manejar caso rechazado
            throw new IllegalStateException("Pago rechazado por servicio de pago: " + response.getMensaje());
        }
    }

    public Cuenta abonarViaje (Long nroCuenta, Double monto) {
        Cuenta cuenta = cuentaRepository.findById(nroCuenta).orElseThrow();;
        Double posibleSaldo= cuenta.getSaldo() - monto;

        if (posibleSaldo < 0) {
            throw new IllegalStateException("Pago rechazado por falta de saldo");
        }

        // llamás al mock de MP
        PagoRequestDTO request = new PagoRequestDTO(nroCuenta, monto);
        PagoResponseDTO response = pagoMockFeignClient.abonarViaje(request);

        if ("APROBADO".equalsIgnoreCase(response.getEstado())) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuentaRepository.save(cuenta);
            return cuenta ;
        } else {
            // si querés, manejar caso rechazado
            throw new IllegalStateException("Pago rechazado por servicio de pago: " + response.getMensaje());
        }
    }
}
