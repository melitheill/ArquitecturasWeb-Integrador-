package org.grupo14.mcsvviaje.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="msvc-factura", url = "htpp://localhost:8002/factura")
public interface FacturaFeignClient {

    @GetMapping("/helloWorld")
    String helloWorld();
}
