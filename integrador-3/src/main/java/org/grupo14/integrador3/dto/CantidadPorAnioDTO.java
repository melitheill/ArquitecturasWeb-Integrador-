package org.grupo14.integrador3.dto;

import lombok.Data;

@Data
public class CantidadPorAnioDTO {
    private int anio;
    private int cantidad;

    public CantidadPorAnioDTO(int anio, Long cantidad) {
        this.anio = anio;
        this.cantidad = Math.toIntExact(cantidad);
    }
}
