package org.example.msvcparada.utils;

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
import java.sql.Timestamp;

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
            if(row.size() >= 5){
//                String idString = row.get(0);
                String nroCuentaString = row.get(0);
                String usuario = row.get(1);
                String montoString = row.get(2);
                String fechaAltaString = row.get(3);
                String tipo_cuenta = row.get(4);

                if(!nroCuentaString.isEmpty() && !usuario.isEmpty() && !montoString.isEmpty() && !fechaAltaString.isEmpty() && !tipo_cuenta.isEmpty()){
                    try{
                        int nroCuenta = Integer.parseInt(nroCuentaString);
                        double monto = Double.parseDouble(montoString);
                        Timestamp fechaAlta = Timestamp.valueOf(fechaAltaString);
                        Cuenta cuenta = new Cuenta(nroCuenta, usuario, monto, fechaAlta, tipo_cuenta);
                        cuentaRepository.save(cuenta);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
