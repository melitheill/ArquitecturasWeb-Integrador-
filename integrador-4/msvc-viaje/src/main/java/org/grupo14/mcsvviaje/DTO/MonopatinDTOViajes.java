package org.grupo14.mcsvviaje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDTOViajes {
    private Long idMonopatin;
    private Long cantidadViajes;

    public MonopatinDTOViajes(long l, int cantidadViajes) {
    }
}
