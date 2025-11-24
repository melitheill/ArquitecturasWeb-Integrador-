package org.example.msvcparada.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    private String nombre;
    private double latitud;
    private double longitud;
}
