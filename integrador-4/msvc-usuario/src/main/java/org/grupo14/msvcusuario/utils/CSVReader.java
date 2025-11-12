package org.grupo14.msvcusuario.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class CSVReader {

    @Autowired
    private UsuarioService usuarioService;

    public CSVReader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-4\\msvc-usuario\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try {
            insertUsuario();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertUsuario() throws IOException {
        for(CSVRecord row : getData("usuarios.csv")){
            if(row.size() >= 4){
                String nombre = row.get(0);
                String mail = row.get(1);
                String latitudS = row.get(2);
                String longitudS = row.get(3);

                if(!nombre.isEmpty() && !mail.isEmpty() && !latitudS.isEmpty() && !longitudS.isEmpty()){
                    try{
                        double latitud = Double.parseDouble(latitudS);
                        double longitud = Double.parseDouble(longitudS);

                        Usuario usuario = new Usuario(nombre, mail, latitud, longitud);
                        usuarioService.save(usuario);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }
}
