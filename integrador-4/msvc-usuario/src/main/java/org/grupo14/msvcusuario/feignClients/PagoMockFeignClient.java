package org.grupo14.msvcusuario.feignClients;

import org.grupo14.msvcusuario.dto.PagoResponseDTO;
import org.grupo14.msvcusuario.dto.PagoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-pagomock", url = "localhost:8008/api/mp")

public interface PagoMockFeignClient {

    @PostMapping("/cargar-saldo")
    public PagoResponseDTO cargarSaldo(@RequestBody PagoRequestDTO request);

    @PostMapping ("/abonar-viaje")
    public PagoResponseDTO abonarViaje(@RequestBody PagoRequestDTO request);


}
