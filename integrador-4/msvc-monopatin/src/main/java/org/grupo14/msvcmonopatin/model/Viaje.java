package org.grupo14.msvcmonopatin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viaje {
    private Long id;
    private int tiempoSinPausas;
    private int tiempoConPausas;
    private int kmRecorridos;
}
