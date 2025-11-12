package org.grupo14.msvcusuario.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.grupo14.msvcusuario.entity.Cuenta;
import org.grupo14.msvcusuario.entity.Usuario;
import org.grupo14.msvcusuario.service.CuentaService;
import org.grupo14.msvcusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSVReader {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CuentaService cuentaService;

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
            insertCuenta();
            insertUsuario();
            agregarCuentaAUsuario();
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

    private void insertCuenta() throws IOException {
        for(CSVRecord row : getData("cuentas.csv")){
            if(row.size() >= 1){
                String idS = row.get(0);

                if(!idS.isEmpty()){
                    try{
                        long id = Long.parseLong(idS);

                        Cuenta cuenta = new Cuenta(id);
                        cuentaService.save(cuenta);
                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
    }

    private void agregarCuentaAUsuario() throws IOException {
        Map<Long, List<Cuenta>> map = new HashMap<>();
        for(CSVRecord row : getData("usuarioscuentas.csv")){
            if(row.size() >= 2){
                String idUsuarioS = row.get(0);
                String idCuentaS = row.get(1);

                if(!idUsuarioS.isEmpty() && !idCuentaS.isEmpty()){
                    try{

                        long idUsuario = Long.parseLong(idUsuarioS);
                        long idCuenta = Long.parseLong(idCuentaS);
                        if(!map.containsKey(idUsuario)){
                            List<Cuenta> cuentas = new ArrayList<>();
                            cuentas.add(new Cuenta(idCuenta));
                            map.put(idUsuario, cuentas);
                        } else {
                            map.get(idUsuario).add(new Cuenta(idCuenta));
                        }

                    } catch (NumberFormatException e){
                        System.err.println("Error" + e.getMessage());
                    }
                }
            }
        }
        map.forEach((idUsuario,cuentas) -> {
           Usuario u = usuarioService.findById(idUsuario);
           u.setCuentas(cuentas);
           usuarioService.save(u);
        });
    }
}
