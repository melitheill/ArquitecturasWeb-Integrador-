package org.grupo14.msvcfactura.feignClients;

import org.grupo14.msvcfactura.Model.Tarifa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="msvc-tarifa",url = "localhost:8008/api/tarifa")
public interface TarifaFeignClient {

    @GetMapping("/activa")
    Tarifa getTarifaActiva();

}
