package org.grupo14.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsoMonopatinesDTO {
    private int kmRecorridos;
    private int cantidadUsos;
    private int tiempoUsoSinPausa;
    private int tiempoUsoConPausa;
}
