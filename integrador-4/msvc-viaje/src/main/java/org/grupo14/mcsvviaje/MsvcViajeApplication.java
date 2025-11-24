package org.grupo14.mcsvviaje;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.grupo14.mcsvviaje.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class MsvcViajeApplication {

    private final LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(MsvcViajeApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        loadDatabase.cargarDatos();
    }

}
