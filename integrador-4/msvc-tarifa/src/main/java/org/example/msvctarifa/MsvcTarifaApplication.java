package org.example.msvctarifa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.msvctarifa.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class MsvcTarifaApplication {


    private final LoadDatabase loadDatabase;
    public static void main(String[] args) {
        SpringApplication.run(MsvcTarifaApplication.class, args);
    }
    @PostConstruct
    public void init() throws IOException {
        loadDatabase.cargarDatos();
    }

}
