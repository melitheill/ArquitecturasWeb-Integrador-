package org.grupo14.msvcusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoResponseDTO {

    private String estado;   // "APROBADO" / "RECHAZADO"
    private String mensaje;
}