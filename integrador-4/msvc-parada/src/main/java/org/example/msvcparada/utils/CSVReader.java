package org.example.msvcparada.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.msvcparada.entity.Parada;
import org.example.msvcparada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;

@Service
public class CSVReader {

    @Autowired
    private ParadaService paradaService;

    public CSVReader(ParadaService paradaService) {
        this.paradaService = paradaService;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-parada\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertParada();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertParada() throws IOException {
        for(CSVRecord row : getData("paradas.csv")){
            if(row.size() >= 3){
                String nombre = row.get(0);
                String latitudS = row.get(1);
                String longitudS = row.get(2);

                if(!nombre.isEmpty() && !latitudS.isEmpty() && !longitudS.isEmpty()){
                    try{
                        double latitud = Double.parseDouble(latitudS);
                        double longitud = Double.parseDouble(longitudS);
                        Parada parada = new Parada(nombre, latitud, longitud);
                        paradaService.save(parada);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
