package org.grupo14.integrador3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Estudiante {
    @Id
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Column
    private int LU;

    public Estudiante() {
    }

    public Estudiante(int id, String nombre, String apellido, int edad, String genero, String ciudad, int LU) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.LU = LU;
    }
}
