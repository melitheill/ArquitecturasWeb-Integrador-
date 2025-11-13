package org.grupo14.msvcusuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroCuenta;
    @JsonIgnore
    @ManyToMany(mappedBy = "cuentas")
    private List<Usuario> usuario;
    private double saldo;
    private Timestamp fechaAlta;
    private boolean isAnulada;

    public Cuenta(double saldo, Timestamp fechaAlta) {
        this.usuario = new ArrayList<>();
        this.saldo = saldo;
        this.fechaAlta = fechaAlta;
        this.isAnulada = false;
    }
}
