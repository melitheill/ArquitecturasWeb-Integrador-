package org.grupo14.msvcmonopatin.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.grupo14.msvcmonopatin.entity.Monopatin;
import org.grupo14.msvcmonopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class CSVReader {

    @Autowired
    private MonopatinRepository monopatinRepository;

    public CSVReader(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-monopatin\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertMonopatines();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertMonopatines() throws IOException {
        for(CSVRecord row : getData("monopatin.csv")){
            if(row.size() >= 4){
                String idString = row.get(0);
                String estado = row.get(1);
                String latitudString = row.get(2);
                String longitudString = row.get(3);
                if(!idString.isEmpty() && !estado.isEmpty() && !latitudString.isEmpty() && !longitudString.isEmpty()){
                    try{
                        Long id = Long.parseLong(idString);
                        double latitud = Double.parseDouble(latitudString);
                        double longitud = Double.parseDouble(longitudString);
                        Monopatin monopatin = new Monopatin(estado, latitud, longitud);
                        monopatinRepository.save(monopatin);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
