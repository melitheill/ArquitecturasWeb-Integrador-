package org.grupo14.integrador3.dto;

import lombok.Getter;

@Getter
public class CarreraDTO {

    private String nombre;
    private int cantidad;

    public CarreraDTO(String carrera, int cantidad) {
          this.nombre = carrera;
          this.cantidad = cantidad;
    }
}
