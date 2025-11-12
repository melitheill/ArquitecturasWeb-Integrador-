package org.example.msvcparada.DTO;

import org.example.msvctarifa.entity.Tarifa;

import java.time.LocalDate;

public class TarifaDTO {
    private int monto_base;
    private int monto_pausa_extensa;
    private LocalDate fechaInicio;

    public TarifaDTO(int monto_base, int monto_pausa_extensa, LocalDate fechaInicio) {
        this.monto_base = monto_base;
        this.monto_pausa_extensa = monto_pausa_extensa;
        this.fechaInicio = fechaInicio;
    }

    public TarifaDTO(Tarifa t) {
            this.monto_base = t.getMonto_base();
            this.fechaInicio = t.getFechaVigencia();
            this.monto_pausa_extensa = t.getMonto_pausa_extensa();
    }

    public int getMonto_base() {
        return monto_base;
    }

    public void setMonto_base(int monto_base) {
        this.monto_base = monto_base;
    }

    public int getMonto_pausa_extensa() {
        return monto_pausa_extensa;
    }

    public void setMonto_pausa_extensa(int monto_pausa_extensa) {
        this.monto_pausa_extensa = monto_pausa_extensa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
