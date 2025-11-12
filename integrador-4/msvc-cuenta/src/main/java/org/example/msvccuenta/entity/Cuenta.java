package org.example.msvccuenta.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nro_cuenta;
    private String usuario;
    private double monto;
    private Timestamp fechaAlta;
    private String tipo_cuenta;

     public Cuenta(int nro_cuenta, String usuario, double monto, Timestamp fechaAlta, String tipo_cuenta) {
         this.nro_cuenta = nro_cuenta;
         this.usuario = usuario;
         this.monto = monto;
         this.fechaAlta = fechaAlta;
         this.tipo_cuenta = tipo_cuenta;
     }

}