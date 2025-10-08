package entity.Identificador;

import entity.Carrera;
import entity.Estudiante;

import java.io.Serializable;

public class EstudianteCarreraID extends Identificador implements Serializable {
    private Estudiante estudiante;
    private Carrera carrera;

    public EstudianteCarreraID() {
    }
    public EstudianteCarreraID(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Carrera getCarrera() {
        return carrera;
    }
}
