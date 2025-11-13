package org.grupo14.msvcmonopatin.feignClients;

import org.grupo14.msvcmonopatin.model.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-viaje", url = "localhost:8001/viaje")
public interface ViajeFeignClient {

    @GetMapping("/test/{idMonopatin}")
    List<Viaje> reporte(@PathVariable Long idMonopatin);

}
