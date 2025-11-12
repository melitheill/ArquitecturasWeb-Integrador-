package org.grupo14.msvcusuario.feignClients;

import org.grupo14.msvcusuario.model.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-viaje", url = "localhost:8001/api/viaje")
public interface ViajeFeignClient {

    @GetMapping("/usuario/{idUsuario}/{yearInicio}/{mesInicio}/{yearFin}/{mesFin}")
    List<Viaje> getViajesUsuario(@PathVariable Long idUsuario, @PathVariable int yearInicio,@PathVariable int mesInicio,@PathVariable int yearFin,   @PathVariable int mesFin);
}
