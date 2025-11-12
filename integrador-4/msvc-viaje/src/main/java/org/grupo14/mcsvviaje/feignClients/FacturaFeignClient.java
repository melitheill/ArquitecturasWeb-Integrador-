package org.grupo14.mcsvviaje.feignClients;

import org.grupo14.mcsvviaje.model.Factura;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="msvc-factura", url = "http://localhost:8002/api/factura")
public interface FacturaFeignClient {

    @PostMapping("")
    Factura save(@RequestBody Factura factura);
}
