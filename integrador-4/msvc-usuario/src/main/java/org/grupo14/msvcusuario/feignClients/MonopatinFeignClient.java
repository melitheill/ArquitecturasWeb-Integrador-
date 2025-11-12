package org.grupo14.msvcusuario.feignClients;


import org.grupo14.msvcusuario.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-monopatin", url = "localhost:8004/api/monopatin")

public interface MonopatinFeignClient {

    @GetMapping("/zona")
    List<Monopatin> getByZona(@RequestParam Double lat, @RequestParam Double lon);

}
