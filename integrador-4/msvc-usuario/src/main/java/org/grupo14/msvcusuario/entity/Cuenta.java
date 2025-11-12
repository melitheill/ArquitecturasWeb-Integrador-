package org.grupo14.msvcusuario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    @Id
    private Long idCuenta;
    @ManyToMany(mappedBy = "cuentas")
    private List<Usuario> usuario;

    public Cuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
        this.usuario = new ArrayList<>();
    }
}
