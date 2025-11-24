package org.grupo14.msvcfactura.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FDTO {
    private LocalDate fecha;
    private Integer valor;

}
