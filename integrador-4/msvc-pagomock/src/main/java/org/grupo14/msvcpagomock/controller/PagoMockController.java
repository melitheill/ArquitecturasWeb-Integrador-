package org.grupo14.msvcpagomock.controller;


import org.grupo14.msvcpagomock.DTO.PagoRequestDTO;
import org.grupo14.msvcpagomock.DTO.PagoResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mp")
public class PagoMockController {


    // Simular cargar saldo vía "MercadoPago"
    @PutMapping("/cargar-saldo")
    public PagoResponseDTO cargarSaldo(@RequestBody PagoRequestDTO request) {

        Long nroCuenta = request.getMercadoPagoCuenta();
        Double saldo = request.getMonto();
        // Lógica totalmente inventada, solo para simular
        String estado;
        String mensaje;

        if (saldo == null || saldo<= 0) {
            estado = "RECHAZADO";
            mensaje = "Monto inválido para cargar saldo, ingrese un monto mayor a 0";
        } else if ( nroCuenta == null) {
            estado = "RECHAZADO";
            mensaje = "No se ha infomado la cuenta de MercadoPago";
        } else {
            estado = "APROBADO";
            mensaje = "Pago se ha realizado correctamente";
        }

       return new PagoResponseDTO(estado,mensaje);

    }
    // Simular cargar saldo vía "MercadoPago"
    @PutMapping  ("/abonarViaje")
    public PagoResponseDTO abonarViaje(@RequestBody PagoRequestDTO request) {

        Long nroCuenta = request.getMercadoPagoCuenta();
        Double monto = request.getMonto();

        // Lógica totalmente inventada, solo para simular
        String estado;
        String mensaje;

        if (monto == null || monto<= 0) {
            estado = "RECHAZADO";
            mensaje = "Monto inválido para abonar viaje,ingrese un monto mayor a 0";
        } else if ( nroCuenta == null) {
            estado = "RECHAZADO";
            mensaje = "No se ha informado la cuenta de MercadoPago";
        } else {
            estado = "APROBADO";
            mensaje = "Pago se ha realizado correctamente";
        }

        return new PagoResponseDTO(estado,mensaje);

    }
}
