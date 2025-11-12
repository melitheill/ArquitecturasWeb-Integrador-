package org.grupo14.msvcmonopatin.feignClients;

import org.grupo14.msvcmonopatin.model.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-parada", url = "localhost:8005/api/parada")

public interface ParadaFeignClient {

    @GetMapping("/{id}")
    Parada getParada(@PathVariable("id") long id);
}
