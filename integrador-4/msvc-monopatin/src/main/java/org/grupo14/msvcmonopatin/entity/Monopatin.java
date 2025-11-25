package org.grupo14.msvcmonopatin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Monopatin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private double latitud;
    private double longitud;

    public Monopatin(String estado, double latitud, double longitud) {
        this.estado = estado;
        this.latitud = latitud;
        this.longitud = longitud;
    }

}
