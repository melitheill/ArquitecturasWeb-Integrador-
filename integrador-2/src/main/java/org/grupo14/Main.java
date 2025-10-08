package org.grupo14;

import entity.Carrera;
import entity.Estudiante;
import helper.EntityManagerHelper;
import service.Servicios;

public class Main {
    public static void main(String[] args) {
        Servicios servicios = new Servicios();

        //realiar una sola vez
        servicios.cargarDatos();

        //a) dar de alta un estudiante
        System.out.println("----------------------------------");
        Estudiante e = new Estudiante(43234223, "Juan", "Mauro", 64, "Masculino", "Azul", 42342);
        servicios.altaEstudiante(e);

        //b) matricular un estudiante en una carrera
        System.out.println("----------------------------------");
        servicios.matricularEstudiante(23322529, 4);

        //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        //Las opciones son "DNI", "nombre", "apellido", "edad", "genero", "ciudad", "LU"
        //cualquier otra opcion o valor nulo ordena por apellido
        System.out.println("----------------------------------");
        System.out.println(servicios.obtenerEstudiantesOrdenados("LU"));

        //d) recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println("----------------------------------");
        System.out.println(servicios.obtenerEstudiantePorLU(42342));

        //e) recuperar todos los estudiantes, en base a su género.
        //Las opciones son "Agender", "Bigender", "Female", "Genderfluid", "Male", "Masculino", "Non-binary", "Polygender"
        System.out.println("----------------------------------");
        System.out.println(servicios.obtenerEstudiantesPorGenero("Polygender"));

        //f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        System.out.println("----------------------------------");
        System.out.println(servicios.obtenerCarrerasConEstudiantesInscriptos());

        //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        System.out.println("----------------------------------");
        System.out.println(servicios.obtenerEstudiantesPorCarreraCiudad(new Carrera(2, "test", 0), "Idvor"));

        //3) Generar un reporte de las carreras, que para cada carrera incluya información de los
        //inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
        //los años de manera cronológica.
        System.out.println("----------------------------------");
        System.out.println(servicios.generarReporte());

        EntityManagerHelper.closeEntityManagerFactory();
    }
}