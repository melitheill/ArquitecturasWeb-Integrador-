package org.example.msvctarifa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int monto_base;
    private int monto_pausa_extensa;
    private LocalDate fechaVigencia;
    private Boolean activa;

    public void desactivar() {
        this.activa = false;
    }

    public void activar() {
        this.activa = true;
    }
    public boolean debeActivarseHoy() {
        return fechaVigencia != null && fechaVigencia.equals(LocalDate.now());
    }

}
