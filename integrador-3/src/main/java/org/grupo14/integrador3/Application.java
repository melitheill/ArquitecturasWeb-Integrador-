package org.grupo14.integrador3;

import jakarta.annotation.PostConstruct;
import org.grupo14.integrador3.utils.LoadDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        loadDatabase.cargarDatos();
    }
}
