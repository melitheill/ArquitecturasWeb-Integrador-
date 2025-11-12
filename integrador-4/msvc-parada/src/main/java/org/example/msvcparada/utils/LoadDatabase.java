package org.example.msvcparada.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoadDatabase {
    private final CSVReader reader;
    public LoadDatabase(CSVReader reader){
        this.reader = reader;
    }

    public void cargarDatos() throws IOException {
        reader.cargarDatos();
    }
}
