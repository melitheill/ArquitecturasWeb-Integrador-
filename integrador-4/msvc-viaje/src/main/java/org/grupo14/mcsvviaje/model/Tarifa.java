package org.grupo14.mcsvviaje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {
    private int monto_base;
    private int monto_pausa_extensa;
}
