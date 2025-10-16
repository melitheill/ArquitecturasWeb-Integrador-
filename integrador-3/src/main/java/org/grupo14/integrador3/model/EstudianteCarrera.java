package org.grupo14.integrador3.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.grupo14.integrador3.model.ids.EstudianteCarreraID;

@Entity
@Data
public class EstudianteCarrera {
    @EmbeddedId
    private EstudianteCarreraID id;
//    @ManyToOne
//    private Estudiante estudiante;
//    @ManyToOne
//    private Carrera carrera;
    @Column
    private int inscripcion;
    @Column
    private int graduacion;
    @Column
    private int antiguedad;

    public EstudianteCarrera() {
    }

    public EstudianteCarrera(int id,Estudiante estudiante, Carrera carrera, int inscripcion, int graduacion, int antiguedad) {
        this.id = new EstudianteCarreraID(estudiante,carrera);
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
    }
}
