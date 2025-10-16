package org.grupo14.integrador3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    private int id;
    @Column
    private String carrera;
    @Column
    private int duracion;
//    @OneToMany(mappedBy = "carrera")
//    private List<EstudianteCarrera> estudianteCarrera;

    public Carrera() {
    }
    public Carrera(int id, String carrera, int duracion) {
        this.id = id;
        this.carrera = carrera;
        this.duracion = duracion;
    }

}
