package org.grupo14.integrador3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Carrera {
    @Id
    private int id;
    @Column
    private String carrera;
    @Column
    private int duracion;

    public Carrera() {
    }
    public Carrera(int id, String carrera, int duracion) {
        this.id = id;
        this.carrera = carrera;
        this.duracion = duracion;
    }

}
