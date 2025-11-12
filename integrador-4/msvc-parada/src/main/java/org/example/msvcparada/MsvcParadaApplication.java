package org.example.msvcparada;

import jakarta.annotation.PostConstruct;
import org.example.msvcparada.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MsvcParadaApplication {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(MsvcParadaApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        loadDatabase.cargarDatos();
    }

}
