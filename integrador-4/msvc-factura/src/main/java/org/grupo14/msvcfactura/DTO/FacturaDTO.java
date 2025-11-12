package org.grupo14.msvcfactura.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private LocalDate fecha;
    private Integer km_recorridos;
    private Integer km_recorridosPausaExtensa;
}
