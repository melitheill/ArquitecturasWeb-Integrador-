package org.grupo14.msvcusuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cuentas")
public class Cuenta {
    @Id
    private Long nroCuenta;
    @JsonIgnore
    @DBRef
    private List<Usuario> usuario;

    private double saldo;
    private Date fechaAlta;
    private boolean isAnulada;

    public Cuenta(double saldo, Date fechaAlta) {
        this.usuario = new ArrayList<>();
        this.saldo = saldo;
        this.fechaAlta = fechaAlta;
        this.isAnulada = false;
    }
}
