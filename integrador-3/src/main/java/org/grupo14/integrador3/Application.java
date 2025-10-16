package org.grupo14.integrador3;

import jakarta.annotation.PostConstruct;
import org.grupo14.integrador3.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        loadDatabase.cargarDatos();
    }
}
