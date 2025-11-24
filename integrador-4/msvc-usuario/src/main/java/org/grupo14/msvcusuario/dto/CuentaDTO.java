package org.grupo14.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long nroCuenta;
    private double saldo;
    private boolean isAnulada;
}
