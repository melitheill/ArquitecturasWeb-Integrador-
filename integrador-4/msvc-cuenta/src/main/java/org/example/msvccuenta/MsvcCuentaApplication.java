package org.example.msvccuenta;

import jakarta.annotation.PostConstruct;
import org.example.msvccuenta.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MsvcCuentaApplication {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(MsvcCuentaApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        loadDatabase.cargarDatos();
    }
}
