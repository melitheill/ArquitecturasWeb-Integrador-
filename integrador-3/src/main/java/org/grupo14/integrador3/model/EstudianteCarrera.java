package org.grupo14.integrador3.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;

@Entity
@Data
public class EstudianteCarrera {
    @EmbeddedId
    private EstudianteCarreraID id;
    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;

    public EstudianteCarrera() {
    }

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion, int antiguedad) {
        this.id = new EstudianteCarreraID(estudiante,carrera);
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }
}
