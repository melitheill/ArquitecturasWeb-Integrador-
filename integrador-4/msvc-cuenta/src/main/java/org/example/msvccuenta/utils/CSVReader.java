package org.example.msvccuenta.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.msvccuenta.entity.Cuenta;
import org.example.msvccuenta.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class CSVReader {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CSVReader(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-cuenta\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertCuenta();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertCuenta() throws IOException {
        for(CSVRecord row : getData("cuentas.csv")){
            if(row.size() >= 4){
//                String idString = row.get(0);
                String nroCuentaString = row.get(1);
                String usuario = row.get(2);
                String montoString = row.get(3);
                String fechaAltaString = row.get(4);
                String tipo_cuenta = row.get(5);

                if(!idString.isEmpty() && !estado.isEmpty() && !latitudString.isEmpty() && !longitudString.isEmpty()){
                    try{
                        Long id = Long.parseLong(idString);
                        double latitud = Double.parseDouble(latitudString);
                        double longitud = Double.parseDouble(longitudString);
                        Cuenta monopatin = new Cuenta(estado, latitud, longitud);
                        cuentaRepository.save(monopatin);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
