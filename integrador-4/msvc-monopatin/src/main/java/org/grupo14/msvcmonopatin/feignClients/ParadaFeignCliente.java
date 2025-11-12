package org.grupo14.msvcmonopatin.feignClients;

import org.grupo14.msvcmonopatin.model.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-parada", url = "localhost:8005/api/parada")

public interface ParadaFeignCliente {

    @GetMapping("/{id}")
    Parada getParada(@PathVariable("id") long id);
}
