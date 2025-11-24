package org.grupo14.mcsvviaje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grupo14.mcsvviaje.entity.Tiempo;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VDTO {
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private Tiempo tiempo;
    private Long paradaInicio;
    private Long paradaFin;
    private Integer kmTotales;
    private Long monopatin;
    private Long usuario;
}
