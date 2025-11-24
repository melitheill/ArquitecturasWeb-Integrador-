package org.grupo14.gateway;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.grupo14.gateway.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class GatewayApplication {

    private final LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @PostConstruct
//    public void init() throws IOException {
//        loadDatabase.cargarDatos();
//    }

}
