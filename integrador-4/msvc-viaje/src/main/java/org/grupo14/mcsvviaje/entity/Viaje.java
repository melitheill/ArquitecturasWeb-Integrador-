package org.grupo14.mcsvviaje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grupo14.mcsvviaje.model.Tarifa;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private Integer kmRecorridos;
    private Integer kmRecorridosPausaExtensa;
    //TODO cambiar por clases reales
    private String paradaInicio;
    private String paradaFin;
    private Integer tarifa;
    private String monopatin;
    private String usuario;
}
