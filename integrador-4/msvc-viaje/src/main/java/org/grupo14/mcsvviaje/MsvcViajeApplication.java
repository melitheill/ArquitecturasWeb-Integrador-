package org.grupo14.mcsvviaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcViajeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcViajeApplication.class, args);
    }

}
