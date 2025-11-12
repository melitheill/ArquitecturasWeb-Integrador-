package org.grupo14.msvcfactura.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.msvccuenta.entity.Cuenta;
import org.example.msvccuenta.repository.CuentaRepository;
import org.grupo14.msvcfactura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;

@Service
public class CSVReader {

    @Autowired
    private FacturaRepository facturaRepository;

    public CSVReader(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-factura\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertFactura();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertFactura() throws IOException {
        for(CSVRecord row : getData("facturas.csv")){
            if(row.size() >= 5){
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
                        facturaRepository.save(cuenta);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
