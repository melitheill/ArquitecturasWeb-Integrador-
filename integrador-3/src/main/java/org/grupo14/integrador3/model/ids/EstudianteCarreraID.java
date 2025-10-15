package org.grupo14.integrador3.model.ids;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.grupo14.integrador3.model.Carrera;
import org.grupo14.integrador3.model.Estudiante;

@Embeddable
@Getter
public class EstudianteCarreraID {
    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;

    public EstudianteCarreraID() {
    }

    public EstudianteCarreraID(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
}
