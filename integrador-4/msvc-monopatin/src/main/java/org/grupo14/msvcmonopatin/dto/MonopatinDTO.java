package org.grupo14.msvcmonopatin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTO {
    private Long id;
    private double kmRecorridos;
    private int tiempoSinPausas;
    private int tiempoConPausas;
}
