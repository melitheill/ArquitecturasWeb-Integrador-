package org.grupo14.mcsvviaje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private Long id;
    private int tiempoSinPausas;
    private int tiempoConPausas;
    private int kmRecorridos;
}
