package org.grupo14.integrador3.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;
import org.grupo14.integrador3.model.EstudianteCarrera;
import org.grupo14.integrador3.repository.CarreraRepository;
import org.grupo14.integrador3.repository.EstudianteCarreraRepository;
import org.grupo14.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class CSVReader {
    @Autowired
    private CarreraRepository cr;
    private EstudianteRepository er;
    private EstudianteCarreraRepository ecr;

    public CSVReader(CarreraRepository cr, EstudianteRepository er, EstudianteCarreraRepository ecr) {
        this.cr = cr;
        this.er = er;
        this.ecr = ecr;
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador-3\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void cargarDatos() throws IOException {
        try{
            insertEstudiantes();
            insertCarreras();
            insertMatriculas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEstudiantes() throws IOException {
        for(CSVRecord row : getData("estudiantes.csv")){
            if(row.size() >= 7){
                String dniString = row.get(0);
                String nombre = row.get(1);
                String apellido = row.get(2);
                String edadString = row.get(3);
                String genero = row.get(4);
                String ciudad = row.get(5);
                String LUString = row.get(6);
                if(!dniString.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edadString.isEmpty() && !genero.isEmpty() && !ciudad.isEmpty() && !LUString.isEmpty()){
                    try{
                        int LU = Integer.parseInt(LUString);
                        int edad = Integer.parseInt(edadString);
                        int dni =  Integer.parseInt(dniString);
                        Estudiante estudiante = new Estudiante(dni, nombre, apellido, edad, genero, ciudad, LU);
                        er.save(estudiante);
                    } catch (NumberFormatException e){
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }

    private void insertCarreras() throws IOException {
        for (CSVRecord row : getData("carreras.csv")) {
            if (row.size() >= 3) {
                String id_carreraString = row.get(0);
                String nombre = row.get(1);
                String duracionString = row.get(2);
                if (!id_carreraString.isEmpty() && !nombre.isEmpty() && !duracionString.isEmpty()) {
                    try {
                        int id_carrera = Integer.parseInt(id_carreraString);
                        int duracion = Integer.parseInt(duracionString);
                        Carrera carrera = new Carrera(id_carrera, nombre, duracion);
                        cr.save(carrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
    private void insertMatriculas() throws IOException {
        for(CSVRecord row : getData("estudianteCarrera.csv")) {
            if(row.size() >= 6) {
                String dniString = row.get(1);
                String id_carreraString = row.get(2);
                String anio_inicioString = row.get(3);
                String anio_finString = row.get(4);
                String antiguedadString = row.get(5);
                if(!dniString.isEmpty() && !id_carreraString.isEmpty() && !anio_inicioString.isEmpty() && !anio_finString.isEmpty()) {
                    try {
                        int dni = Integer.parseInt(dniString);
                        int id_carrera = Integer.parseInt(id_carreraString);
                        int anio_inicio = Integer.parseInt(anio_inicioString);
                        int anio_fin = Integer.parseInt(anio_finString);
                        int antiguedad = Integer.parseInt(antiguedadString);

                        Estudiante estudiante = er.findById(dni).get();
                        Carrera carrera = cr.findById(id_carrera).get();
                        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera, anio_inicio, anio_fin, antiguedad);
                        ecr.save(estudianteCarrera);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de dirección: " + e.getMessage());
                    }
                }
            }
        }
    }
}
