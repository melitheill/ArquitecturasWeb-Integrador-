package org.grupo14.mcsvviaje.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.grupo14.mcsvviaje.entity.Viaje;
import org.grupo14.mcsvviaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;

@Service
public class CSVReader {

    @Autowired
    private ViajeService viajeService;

    public CSVReader(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-viaje\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertViaje();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertViaje() throws IOException {
        for(CSVRecord row : getData("viajes.csv")){
            if(row.size() >= 9){
                String fiS = row.get(0);
                String ffS = row.get(1);
                String kmrS = row.get(2);
                String kmpS = row.get(3);
                String pausaS = row.get(4);
                String piS = row.get(5);
                String pfS = row.get(6);
                String monopatinS = row.get(7);
                String usuarioS = row.get(8);

                if(!fiS.isEmpty() && !ffS.isEmpty() && !kmrS.isEmpty()
                        && !kmpS.isEmpty() && !pausaS.isEmpty() && !piS.isEmpty()
                        && !pfS.isEmpty()
                        && !monopatinS.isEmpty() && !usuarioS.isEmpty()){
                    try{
                        Timestamp fi = Timestamp.valueOf(fiS);
                        Timestamp ff = Timestamp.valueOf(ffS);
                        int kmr = Integer.parseInt(kmrS);
                        int kmp = Integer.parseInt(kmpS);
                        int pausa = Integer.parseInt(pausaS);
                        long pi = Long.parseLong(piS);
                        long pf = Long.parseLong(pfS);
                        long monopatin = Long.parseLong(monopatinS);
                        long usuario = Long.parseLong(usuarioS);

                        Viaje viaje = new Viaje(fi, ff, kmr, kmp, pausa, pi, pf, monopatin, usuario);
                        viajeService.save(viaje);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
