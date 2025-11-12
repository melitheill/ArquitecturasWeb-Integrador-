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
    @OneToOne
    private Tiempo tiempo;
    private Integer pausa;
    //TODO cambiar por clases reales
    private Long paradaInicio;
    private Long paradaFin;
    private Integer tarifa;
    private Long monopatin;
    private Long usuario;

    public Viaje(Timestamp fechaHoraInicio, Timestamp fechaHoraFin, Integer kmRecorridos, Integer kmRecorridosPausaExtensa,  Integer pausa, Long paradaInicio, Long paradaFin, Integer tarifa, Long monopatin, Long usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kmRecorridos = kmRecorridos;
        this.kmRecorridosPausaExtensa = kmRecorridosPausaExtensa;
        this.tiempo = null;
        this.pausa = pausa;
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.tarifa = tarifa;
        this.monopatin = monopatin;
        this.usuario = usuario;
    }
}
